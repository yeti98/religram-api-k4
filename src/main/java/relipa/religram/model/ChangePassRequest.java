package relipa.religram.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePassRequest {
    @NotEmpty(message = "Current password not match or new password invalid")
    @Size(min = 6, max = 32, message = "Current password not match or new password invalid")
    private String currentPassword;
    @NotEmpty(message = "Current password not match or new password invalid")
    @Size(min = 6, max = 32, message = "Current password not match or new password invalid")
    private String newPassword;
}