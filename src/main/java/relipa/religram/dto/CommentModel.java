package relipa.religram.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class CommentModel {
    private int id;
    private UserModel user;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private List<HashtagModel> hashtags;
    private List<MentionModel> mentions;

    public CommentModel(int id, UserModel user, String content, LocalDateTime createAt, LocalDateTime updateAt, List<HashtagModel> hashtags, List<MentionModel> mentions) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.hashtags = hashtags;
        this.mentions = mentions;
    }
}
