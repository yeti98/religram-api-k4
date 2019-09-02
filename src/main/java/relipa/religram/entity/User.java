package relipa.religram.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String fullname;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    @Column(unique = true)
    private String username;
    @NotNull
    private String password;
    private String avatar;

    @ManyToMany()
    @JoinTable(name = "follow", joinColumns = @JoinColumn(name = "followee_id"), inverseJoinColumns = @JoinColumn(name = "follower_id"))
    private List<User> followers;

    @ManyToMany()
    @JoinTable(name = "follow", joinColumns = @JoinColumn(name = "follower_id"), inverseJoinColumns = @JoinColumn(name = "followee_id"))
    private List<User> following;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(@NotNull String fullname, @NotNull String email, @NotNull String username, @NotNull String password) {
        this.fullname = fullname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

}