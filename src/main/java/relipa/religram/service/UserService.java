package relipa.religram.service;

import relipa.religram.entity.User;
import relipa.religram.model.*;

<<<<<<< HEAD
import javax.servlet.http.HttpServletRequest;
=======
import relipa.religram.model.ChangePassRequest;
import relipa.religram.model.LoginFBRequest;
import relipa.religram.model.LoginRequest;
import relipa.religram.model.LoginResponse;
import relipa.religram.model.SignupRequest;
import relipa.religram.model.UserModel;
>>>>>>> 31c2769042cf0389fb903bef4df70da73f09279a

public interface UserService {
    public LoginResponse getSignupResponse(SignupRequest signupRequest);

    public LoginResponse getLoginResponse(LoginRequest loginRequest);

    public LoginResponse getLoginFbResponse(LoginFBRequest loginFBRequest);

    public void changePassword(HttpServletRequest httpServlet, ChangePassRequest request);

    public void resetPassword(String email);

<<<<<<< HEAD
    User getCurrentUser();
=======
    public UserModel getUserById(int id);

>>>>>>> 31c2769042cf0389fb903bef4df70da73f09279a
}