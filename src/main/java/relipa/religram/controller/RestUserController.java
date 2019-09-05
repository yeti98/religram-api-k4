package relipa.religram.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import relipa.religram.dto.PostModel;
import relipa.religram.entity.Post;
import relipa.religram.model.LoginResponse;
import relipa.religram.model.PagingResponse;
import relipa.religram.model.UserModel;
import relipa.religram.model.UserUpdateRequest;
import relipa.religram.service.PostService;
import relipa.religram.service.UserService;
import utils.Entity2DTO;

@RestController
public class RestUserController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @GetMapping("/user/{id}")
    public UserModel getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PutMapping("/user")
    public LoginResponse updateUser(@Valid @RequestBody UserUpdateRequest request) {
        return userService.updateUser(request);
    }

    @GetMapping("/user/{id}/posts")
    public PagingResponse getPostByUser(@PathVariable int id, @RequestParam int page) {
        Page<Post> result = postService.getPostByUser(id, page);
        int totalPage = result.getTotalPages();
        List<PostModel> posts = result.stream().map(Entity2DTO::toPostModel).collect(Collectors.toList());
        for (PostModel temp : posts) {
            temp.setComments(null);
        }
        return new PagingResponse(totalPage, posts);
    }
}