package relipa.religram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import relipa.religram.dto.PostModel;
import relipa.religram.entity.Post;
import relipa.religram.model.PagingResponse;
import relipa.religram.service.PostService;
import utils.Entity2DTO;

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
}
