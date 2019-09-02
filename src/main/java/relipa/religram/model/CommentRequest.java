package relipa.religram.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class CommentRequest {
    @NotNull(message = "must not be blank")
    private Integer userId;
    @NotNull(message = "must not be blank")
    @Size(min = 1, max = 1200, message = "comment co do dai tu 1 den 1200 tu")
    private String comment;
    private List<@Size(min = 1, max = 100, message = "hashtag co do dai tu 1 den 100 tu") String> hashtags;
}
