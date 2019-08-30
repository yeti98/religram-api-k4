package relipa.religram.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {

    @Size(min = 2, max = 32, message = "Username co do dai tu 2-32")
    @NotEmpty(message = "Username khong duoc de trong")
    private String username;
    @NotEmpty(message = "Password khong duoc de trong")
    @Size(min = 6, message = "Password co do dai toi theu la 6")
    private String password;

    public LoginRequest(
            @Size(min = 2, max = 32, message = "Username co do dai tu 2-32") @NotEmpty(message = "Username khong duoc de trong") String username,
            @NotEmpty(message = "Password khong duoc de trong") @Size(min = 6, message = "Password co do dai toi theu la 6") String password) {
        this.username = username;
        this.password = password;
    }

}