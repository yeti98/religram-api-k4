package relipa.religram.custom_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import relipa.religram.entity.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {

}
