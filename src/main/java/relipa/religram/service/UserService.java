package relipa.religram.service;

import relipa.religram.entity.User;
import relipa.religram.model.*;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    public LoginResponse getSignupResponse(SignupRequest signupRequest);

    public LoginResponse getLoginResponse(LoginRequest loginRequest);

    public LoginResponse getLoginFbResponse(LoginFBRequest loginFBRequest);

    public void changePassword(HttpServletRequest httpServlet, ChangePassRequest request);

    public void resetPassword(String email);

    User getCurrentUser();
}