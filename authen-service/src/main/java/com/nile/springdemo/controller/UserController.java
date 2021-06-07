package com.nile.springdemo.controller;

import com.nile.springdemo.DTO.LoginRequest;
import com.nile.springdemo.DTO.RegisterResponse;
import com.nile.springdemo.exception.UserException;
import com.nile.springdemo.model.User;
import com.nile.springdemo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody User user) throws UserException {
        System.out.println(user + "=====");
        User newUser = userService.createUser(user);
        ModelMapper modelMapper = new ModelMapper();
        RegisterResponse response = modelMapper.map(newUser , RegisterResponse.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) throws UserException {
        String res = userService.loginUser(loginRequest);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<String> refreshToken() throws UserException {
        String response = userService.refreshToken();
        return ResponseEntity.ok(response);
    }

}
