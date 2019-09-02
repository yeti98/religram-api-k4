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
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String hashtag;

    private LocalDateTime timeStamp;

    @ManyToMany
    @JoinTable(name = "hashtag_comment", joinColumns = @JoinColumn(name = "hashtag_id"), inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private List<Comment> comments;

    public Hashtag(@NotNull String hashtag, LocalDateTime timeStamp, List<Comment> comments) {
        this.hashtag = hashtag;
        this.timeStamp = timeStamp;
        this.comments = comments;
    }
}