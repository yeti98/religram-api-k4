package relipa.religram.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import relipa.religram.custom_repository.UserRepository;
import relipa.religram.model.*;
import relipa.religram.service.JwtService;
import relipa.religram.service.UserServiceImpl;

@RestController
@RequestMapping("/auth")
public class RestAuthController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @PostMapping("/signup")
    public LoginResponse signup(@Valid @RequestBody SignupRequest signupRequest) {
        return userServiceImpl.getSignupResponse(signupRequest);
    }

}