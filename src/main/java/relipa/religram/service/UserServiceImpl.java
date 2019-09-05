package relipa.religram.service;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import relipa.religram.configer.security.CustomUserDetails;
import relipa.religram.custom_repository.PhotoRepository;
import relipa.religram.custom_repository.UserRepository;
import relipa.religram.entity.Photo;
import relipa.religram.entity.User;
import relipa.religram.exceptionhandle.ApiMissingException;
import relipa.religram.exceptionhandle.EmailIsAlreadyTakenException;
import relipa.religram.exceptionhandle.UsernameIsAlreadyTakenException;
import relipa.religram.model.*;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Value("${app_secret}")
    private String appSecret;

    @Autowired
    private MailService mailService;

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public LoginResponse getSignupResponse(SignupRequest signupRequest) {
        User userCheck = userRepository.findByUsername(signupRequest.getUsername());
        if (userCheck != null) {
            throw new UsernameIsAlreadyTakenException();
        }
        User userCheckEmail = userRepository.findByEmail(signupRequest.getEmail());
        if (userCheckEmail != null) {
            throw new EmailIsAlreadyTakenException();
        }
        String username = signupRequest.getUsername();
        String fullname = signupRequest.getFullname();
        String password = passwordEncoder.encode(signupRequest.getPassword());
        String email = signupRequest.getEmail();
        User user = new User(fullname, email, username, password);
        userRepository.save(user);
        String token = jwtService.generateToken(new CustomUserDetails(user));
        UserModel userModel = new UserModel(user.getId(), user.getFullname(), user.getEmail(), user.getUsername(),
                user.getAvatar());
        return new LoginResponse(userModel, token);
    }

    @Override
    public LoginResponse getLoginResponse(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateToken((CustomUserDetails) authentication.getPrincipal());
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(customUserDetails.getUsername());
        UserModel userModel = new UserModel(user.getId(), user.getFullname(), user.getEmail(), user.getUsername(),
                user.getAvatar());
        LoginResponse loginResponse = new LoginResponse(userModel, jwt);
        return loginResponse;
    }

    public com.restfb.types.User getUserFB(String accessToken) {
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, appSecret, Version.LATEST);
        com.restfb.types.User user = facebookClient.fetchObject("me", com.restfb.types.User.class,
                Parameter.with("fields", "id,name,email,picture{url}"));
        return user;
    }

    @Override
    public LoginResponse getLoginFbResponse(LoginFBRequest loginFBRequest) {
        com.restfb.types.User user = getUserFB(loginFBRequest.getAccessToken());
        String email = user.getEmail();
        relipa.religram.entity.User user2 = userRepository.findByEmail(email);
        String jwt = jwtService.generateToken(new CustomUserDetails(user2));
        UserModel userModel = new UserModel(user2.getId(), user2.getFullname(), user2.getEmail(), user2.getUsername(),
                user2.getAvatar());
        LoginResponse loginResponse = new LoginResponse(userModel, jwt);
        return loginResponse;
    }

    @Override
    public void changePassword(HttpServletRequest httpServlet, ChangePassRequest request) {
        String token = httpServlet.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            token = token.substring(7);
        } else
            throw new RuntimeException("Api is invalid");
        CustomUserDetails cusUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        User user = cusUser.getUser();
        if (user == null)
            throw new RuntimeException("Api is invalid");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), request.getCurrentPassword()));
        if (authentication.isAuthenticated()) {
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(user);
        }
    }

    @Override
    public void resetPassword(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new RuntimeException("Email doesn't exist");
        else {
            String jwt = jwtService.generateToken(new CustomUserDetails(user));
            String context = "http://localhost:8082/api/v1/auth/verify/" + jwt;
            String subject = "Reset password religram";
            mailService.sendMail(email, subject, context);
        }
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new ApiMissingException();
        }
        CustomUserDetails cusUser = (CustomUserDetails) authentication.getPrincipal();
        return cusUser.getUser();
    }

    @Override
    public UserModel getUserById(int id) {
        CustomUserDetails cusUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        User user = cusUser.getUser();
        if (user == null)
            throw new RuntimeException("API not found or invalid");
        user = userRepository.findById(id).get();
        if (user == null)
            throw new RuntimeException("User not found");
        return new UserModel(user.getId(), user.getUsername(), user.getFullname(), user.getEmail(), user.getAvatar());
    }

    @Override
    public LoginResponse updateUser(UserUpdateRequest request) {
        CustomUserDetails customUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        User user = customUser.getUser();
        user.setUsername(request.getUsername());
        user.setFullname(request.getFullname());
        Photo photo = PhotoServiceImpl.decodeToImage(user.hashCode(), request.getAvatar());
        photoRepository.save(photo);
        user.setAvatar(photo.getPhotoUri());
        userRepository.save(user);
        String jwt = jwtService.generateToken(new CustomUserDetails(user));
        UserModel userModel = new UserModel(user.getId(), user.getFullname(), user.getEmail(), user.getUsername(),
                user.getAvatar());
        LoginResponse loginResponse = new LoginResponse(userModel, jwt);
        return loginResponse;
    }
}