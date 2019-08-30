package relipa.religram.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {
    private UserModel user;
    private String token;

    public LoginResponse(UserModel user, String token) {
        this.user = user;
        this.token = token;
    }

}