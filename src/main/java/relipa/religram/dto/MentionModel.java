package relipa.religram.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MentionModel {
    private int id;
    private UserModel user;
    private CommentModel comment;
    private LocalDateTime createAt;

    public MentionModel(int id, UserModel user, CommentModel comment, LocalDateTime createAt) {
        this.id = id;
        this.user = user;
        this.comment = comment;
        this.createAt = createAt;
    }
}
