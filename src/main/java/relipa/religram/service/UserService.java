package relipa.religram.service;

import relipa.religram.model.LoginRequest;
import relipa.religram.model.LoginResponse;
import relipa.religram.model.SignupRequest;

public interface UserService {
    public LoginResponse getSignupResponse(SignupRequest signupRequest);

    public LoginResponse getLoginResponse(LoginRequest loginRequest);
}