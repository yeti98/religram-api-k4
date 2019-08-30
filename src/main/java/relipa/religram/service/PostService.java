package relipa.religram.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import relipa.religram.entity.Comment;
import relipa.religram.entity.Post;

@Service
public interface PostService {
    Page<Post> getPostsByPage(Integer page);

    Page<Comment> getCommentsByPage(Integer postId, Integer page);

    Post getPostById(Integer postId);
}
