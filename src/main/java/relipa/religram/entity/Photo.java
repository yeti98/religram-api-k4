package relipa.religram.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String photoUri;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public Photo(@NotNull String photoUri, LocalDateTime createAt, LocalDateTime updateAt) {
        this.photoUri = photoUri;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}