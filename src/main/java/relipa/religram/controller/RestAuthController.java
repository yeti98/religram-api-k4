package relipa.religram.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import relipa.religram.custom_repository.UserRepository;
import relipa.religram.model.*;
import relipa.religram.service.JwtService;
import relipa.religram.service.UserService;

@RestController
@RequestMapping("/auth")
public class RestAuthController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @PostMapping("/signup")
    public LoginResponse signup(@Valid @RequestBody SignupRequest signupRequest) {
        return userService.getSignupResponse(signupRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.getLoginResponse(loginRequest);
    }

    @PostMapping("/login/facebook")
    public LoginResponse loginFb(@RequestBody LoginFBRequest loginFbRequest) {
        return userService.getLoginFbResponse(loginFbRequest);
    }

    @PostMapping("/changepassword")
    public void changePassword(HttpServletRequest httpRequest,
            @Valid @RequestBody ChangePassRequest changePassRequest) {
        userService.changePassword(httpRequest, changePassRequest);
    }

    @PostMapping("/resetpassword")
    public void resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        userService.resetPassword(resetPasswordRequest.getEmail());
    }

    @GetMapping("/verify/{token}")
    public String verify(@PathVariable String token) {
        if (jwtService.validateToken(token))
            return "OK";
        else
            throw new RuntimeException("Token is invalid!");
    }

}