package relipa.religram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import relipa.religram.custom_repository.CommentRepository;
import relipa.religram.custom_repository.PostRepository;
import relipa.religram.entity.Comment;
import relipa.religram.entity.Post;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Value("${app.properties.default_post_per_page}")
    private Integer PostPerPage;
    @Value("${app.properties.default_comment_per_page}")
    private Integer CommentPerPage;

    @Override
    public Page<Post> getPostsByPage(Integer page) {
        Pageable pageable = PageRequest.of(page, PostPerPage, Sort.by("createAt").descending());
        return postRepository.findAll(pageable);
    }

    @Override
    public Page<Comment> getCommentsByPage(Integer postId, Integer page) {
        Post post = getPostById(postId);
        Pageable pageable = PageRequest.of(page, CommentPerPage, Sort.by("createAt").descending());
        return commentRepository.findByPostId(post.getId(), pageable);
    }

    @Override
    public Post getPostById(Integer postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (!post.isPresent()) {
            try {
                throw new NoSuchElementException("Could not find entity");
            } catch (Exception ignored) {
            }
        }
        return post.get();
    }
}
