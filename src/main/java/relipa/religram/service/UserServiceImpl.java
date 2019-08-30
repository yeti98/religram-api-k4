package relipa.religram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import relipa.religram.configer.security.CustomUserDetails;
import relipa.religram.custom_repository.UserRepository;
import relipa.religram.entity.User;
import relipa.religram.exceptionhandle.EmailIsAlreadyTakenException;
import relipa.religram.exceptionhandle.UsernameIsAlreadyTakenException;
import relipa.religram.model.LoginRequest;
import relipa.religram.model.LoginResponse;
import relipa.religram.model.SignupRequest;
import relipa.religram.model.UserModel;

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

}