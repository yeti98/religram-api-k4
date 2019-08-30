package relipa.religram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import relipa.religram.model.UserModel;
import relipa.religram.service.PostService;
import relipa.religram.service.UserService;

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
}