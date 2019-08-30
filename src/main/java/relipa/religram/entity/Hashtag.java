package relipa.religram.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

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
}