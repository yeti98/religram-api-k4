package relipa.religram.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private List<Comment> comments;

    private boolean isLiked = false;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private List<Photo> photos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private List<Like> likes;
    @NotNull
    private LocalDateTime createAt;
    @NotNull
    private LocalDateTime updateAt;

    public Post(String content, @NotNull User user, List<Comment> comments, boolean isLiked, List<Photo> photos, List<Like> likes, @NotNull LocalDateTime createAt, @NotNull LocalDateTime updateAt) {
        this.content = content;
        this.user = user;
        this.comments = comments;
        this.isLiked = isLiked;
        this.photos = photos;
        this.likes = likes;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}