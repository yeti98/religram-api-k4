package relipa.religram.custom_repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import relipa.religram.entity.Comment;

@Repository
@RepositoryRestResource(exported = false)
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("select c from Comment c where post_id = :id")
    Page<Comment> findByPostId(Integer id, Pageable pageable);
}
