package relipa.religram.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserModel {

    private int id;
    private String username;
    private String fullname;
    private String email;
    private String avatar;


    public UserModel(int id, String fullname, String email, String username, String avatar) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.avatar = avatar;
    }
}