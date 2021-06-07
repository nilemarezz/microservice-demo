package com.nile.springdemo.controller;

import com.nile.springdemo.exception.BaseException;
import com.nile.springdemo.model.User;
import com.nile.springdemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class TestApi {

    @Autowired
    TestService testService;

    @GetMapping("/home")
    public String showIndex(){
        return "hello";
    }

    @PostMapping("/")
    public ResponseEntity<String> register(@RequestBody User user) throws BaseException {
        String response = null;
            response = testService.register(user);
//        return testService.register(user);
        return ResponseEntity.ok(response);
    }
}
