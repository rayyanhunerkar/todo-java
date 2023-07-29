package com.rayyanhunerkar.todolist.controller;

import com.rayyanhunerkar.todolist.POJO.Users.LoginRequest;
import com.rayyanhunerkar.todolist.POJO.Users.SignUpRequest;
import com.rayyanhunerkar.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<Object> createUser(@RequestBody @NotNull SignUpRequest request) throws Exception {
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.OK);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<Object> login(@RequestBody @NotNull LoginRequest request) throws Exception {
        return new ResponseEntity<>(userService.loginUser(request), HttpStatus.OK);
    }

}
