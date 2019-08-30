package relipa.religram.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import relipa.religram.entity.Photo;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class PostModel {
    private int id;
    private String content;
    private UserModel user;
    private List<CommentModel> comments;
    private List<Photo> photos;
    private List<LikeModel> likes;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public PostModel(int id, String content, UserModel userModel, List<CommentModel> comments, List<Photo> photos, List<LikeModel> likes, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.content = content;
        this.user = userModel;
        this.comments = comments;
        this.photos = photos;
        this.likes = likes;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
