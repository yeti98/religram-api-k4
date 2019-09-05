package relipa.religram.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateRequest {
    @NotEmpty(message = "Username khong duoc de trong")
    @Size(min = 6, max = 32, message = "Username phai co tu 6 den 32 ki tu")
    private String username;
    @NotEmpty(message = "Name khong duoc de trong")
    private String fullname;
    private String avatar;

}