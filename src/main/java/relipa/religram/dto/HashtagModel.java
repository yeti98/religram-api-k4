package relipa.religram.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class HashtagModel {
    private int id;
    private String hashtag;
    private LocalDateTime timeStamp;

    public HashtagModel(int id, String hashtag, LocalDateTime timeStamp) {
        this.id = id;
        this.hashtag = hashtag;
        this.timeStamp = timeStamp;
    }
}
