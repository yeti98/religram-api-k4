package relipa.religram.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class LikeModel {
    private int id;
    private UserModel user;
    private LocalDateTime timeStamp;

    public LikeModel(int id, UserModel user, LocalDateTime timeStamp) {
        this.id = id;
        this.user = user;
        this.timeStamp = timeStamp;
    }
}
