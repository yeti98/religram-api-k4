package relipa.religram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import relipa.religram.dto.CommentModel;
import relipa.religram.dto.PostModel;
import relipa.religram.entity.Comment;
import relipa.religram.entity.Post;
import relipa.religram.model.PagingResponse;
import relipa.religram.service.PostService;
import utils.Entity2DTO;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
public class PostRESTController {
    @Autowired
    private PostService postService;

    @GetMapping("/post")
    public PagingResponse getAllPost(@RequestParam("page") String page) {
        Page<Post> result = postService.getPostsByPage(Integer.valueOf(page));
        int totalPages = result.getTotalPages();
        List<PostModel> posts = result.stream().map(Entity2DTO::toPostModel).collect(Collectors.toList());
        return new PagingResponse(totalPages, posts);
    }

    @GetMapping("/post/{id}/comment")
    public PagingResponse getCommentsByPostId(@PathVariable("id") String postId, @RequestParam("page") String page) {
        Page<Comment> result = postService.getCommentsByPage(Integer.valueOf(postId), Integer.valueOf(page));
        int totalPage = result.getTotalPages();
        List<CommentModel> comments = result.stream().map(Entity2DTO::toCommentModel).collect(Collectors.toList());
        return new PagingResponse(totalPage, comments);
    }

    @PostMapping("/post/{id}/like")
    public void like_unlike(@Valid @PathVariable("id") String postId) {
        postService.changeLikeState(Integer.valueOf(postId));
    }

    @PostMapping("/post/{id}/comment")
    public void postComment(@Valid @PathVariable("id") String postId,
                            @Valid @RequestBody CommentRequest commentRequest) {
        postService.saveNewComment(Integer.valueOf(postId), commentRequest);
    }
}
