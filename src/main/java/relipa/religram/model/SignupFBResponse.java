package relipa.religram.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupFBResponse {
    private String username;
    private String fullname;
    private String email;
    private String avatar;
}