package relipa.religram.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupRequest {
    @NotEmpty(message = "Username khong duoc de trong")
    @Size(min = 6, max = 32, message = "Username phai co tu 6 den 32 ki tu")
    private String username;
    @Size(min = 6, max = 32, message = "Password phai co tu 6 den 32 ki tu")
    private String password;
    @NotEmpty(message = "Name khong duoc de trong")
    private String fullname;
    @Email(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", message = "Email khong hop le")
    private String email;

    public SignupRequest(
            @NotEmpty(message = "Username khong duoc de trong") @Size(min = 6, max = 32, message = "Username phai co tu 6 den 32 ki tu") String username,
            @Size(min = 6, max = 32, message = "Password phai co tu 6 den 32 ki tu") String password,
            @NotEmpty(message = "Name khong duoc de trong") String fullname,
            @Email(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", message = "Email khong hop le") String email) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
    }

}