package relipa.religram.custom_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import relipa.religram.entity.Hashtag;

public interface HashtagRepository extends JpaRepository<Hashtag, Integer> {
    Hashtag findByHashtag(String hashtag);
}
