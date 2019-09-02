package relipa.religram.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResetPasswordRequest {
    @NotEmpty(message = "Email is required")
    @Email(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", message = "Email is invalid")
    private String email;
}