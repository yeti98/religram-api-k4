package relipa.religram.service;

import relipa.religram.entity.User;
import relipa.religram.model.*;

import javax.servlet.http.HttpServletRequest;
import relipa.religram.model.ChangePassRequest;
import relipa.religram.model.LoginFBRequest;
import relipa.religram.model.LoginRequest;
import relipa.religram.model.LoginResponse;
import relipa.religram.model.SignupRequest;
import relipa.religram.model.UserModel;

public interface UserService {
    public LoginResponse getSignupResponse(SignupRequest signupRequest);

    public LoginResponse getLoginResponse(LoginRequest loginRequest);

    public LoginResponse getLoginFbResponse(LoginFBRequest loginFBRequest);

    public void changePassword(HttpServletRequest httpServlet, ChangePassRequest request);

    public void resetPassword(String email);

    User getCurrentUser();
    public UserModel getUserById(int id);

}