package relipa.religram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import relipa.religram.custom_repository.CommentRepository;
import relipa.religram.custom_repository.PostRepository;
import relipa.religram.entity.*;
import relipa.religram.model.CommentRequest;
import relipa.religram.model.PostRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private HashtagServiceImpl hashtagService;
    @Autowired
    private PhotoServiceImpl photoService;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeLikeState(Integer postId) {
        Post post = getPostById(postId);
        List<Like> likes = post.getLikes();
        if (likes == null)
            likes = new ArrayList<>();
        User currentUser = userService.getCurrentUser();
        boolean isCurrentUserLiked = false;
        Like temp = null;
        for (Like like : likes) {
            if (like.getUser().getId() == currentUser.getId()) {
                isCurrentUserLiked = true;
                temp = like;
                break;
            }
        }
        if (!isCurrentUserLiked) {
            if (!post.isLiked())
                post.setLiked(true);
            Like like = new Like(userService.getCurrentUser(), LocalDateTime.now());
            likes.add(like);
        } else {
            likes.remove(temp);
            if (likes.size() == 0)
                post.setLiked(false);
            post.setLikes(likes);
        }
        postRepository.save(post);
    }

    @Override
    public void saveNewComment(Integer postId, CommentRequest commentRequest) {
        Post post = getPostById(postId);
        User user = userService.getCurrentUser();
        String content = commentRequest.getComment();
        hashtagService.saveNewHashtag(commentRequest.getHashtags());
        List<Hashtag> hashtags = hashtagService.findByHashtag(commentRequest.getHashtags());
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreateAt(LocalDateTime.now());
        comment.setUpdateAt(LocalDateTime.now());
        comment.setHashtags(hashtags);
        comment.setUser(user);
        commentRepository.save(comment);
        post.getComments().add(comment);
        postRepository.save(post);
    }

    @Override
    public void saveNewPost(PostRequest postRequest) {
        Integer userid = postRequest.getUserId();
        User user = userService.getCurrentUser();
        List<String> hashtags = postRequest.getHashtags();
        List<String> images = postRequest.getImages();
        List<Photo> photos = images.stream().map(s -> PhotoServiceImpl.decodeToImage(user.hashCode(), s))
                .collect(Collectors.toList());
        photoService.saveNewPhotos(photos);
        hashtagService.saveNewHashtag(hashtags);
        Post post = new Post();
        post.setContent(postRequest.getCaption());
        post.setUser(user);
        post.setComments(new ArrayList<>());
        post.setLiked(false);
        post.setPhotos(photos);
        post.setLikes(new ArrayList<>());
        post.setCreateAt(LocalDateTime.now());
        post.setUpdateAt(LocalDateTime.now());
        postRepository.save(post);
    }

    @Override
    public Page<Post> getPostByUser(Integer userId, Integer page) {
        User user = userService.findById(userId);
        Pageable pageable = PageRequest.of(page, PostPerPage, Sort.by("createAt").descending());
        Page<Post> posts = postRepository.findByUser(user, pageable);
        return posts;
    }
}
