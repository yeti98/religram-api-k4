package relipa.religram.service;

import javax.servlet.http.HttpServletRequest;

import relipa.religram.model.ChangePassRequest;
import relipa.religram.model.LoginFBRequest;
import relipa.religram.model.LoginRequest;
import relipa.religram.model.LoginResponse;
import relipa.religram.model.SignupRequest;

public interface UserService {
    public LoginResponse getSignupResponse(SignupRequest signupRequest);

    public LoginResponse getLoginResponse(LoginRequest loginRequest);

    public LoginResponse getLoginFbResponse(LoginFBRequest loginFBRequest);

    public void changePassword(HttpServletRequest httpServlet, ChangePassRequest request);

    public void resetPassword(String email);
}