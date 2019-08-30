package relipa.religram.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import relipa.religram.entity.Post;

@Service
public interface PostService {
    Page<Post> getPostsByPage(Integer page);
}
