package relipa.religram.model;

import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginFBRequest {
    @NotEmpty(message = "AccessToken is required")
    private String accessToken;
}