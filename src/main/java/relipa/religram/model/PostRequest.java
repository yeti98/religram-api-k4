package relipa.religram.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class PostRequest {
    @NotNull(message = "must not be blank")
    private int userId;
    @Size(min = 0, max = 1200, message = "Noi dung khong dai qua 1200 ky tu")
    private String caption;
    @NotNull(message = "must not be blank")
    private List<@Pattern(regexp = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$") String> images;
    @NotNull(message = "must not be blank")
    private List<String> hashtags;

    public PostRequest(@NotNull(message = "must not be blank") int userId, @Size(min = 0, max = 1200, message = "Noi dung khong dai qua 1200 ky tu") String caption, @Pattern(regexp = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$") List<String> images, @NotNull(message = "must not be blank") List<String> hashtags) {
        this.userId = userId;
        this.caption = caption;
        this.images = images;
        this.hashtags = hashtags;
    }
}
