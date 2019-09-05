package relipa.religram.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import relipa.religram.model.LoginResponse;
import relipa.religram.model.UserModel;
import relipa.religram.model.UserUpdateRequest;
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

    @PutMapping("/user")
    public LoginResponse updateUser(@Valid @RequestBody UserUpdateRequest request) {
        return userService.updateUser(request);
    }
}