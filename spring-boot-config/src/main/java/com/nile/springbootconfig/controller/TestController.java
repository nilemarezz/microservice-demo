package com.nile.springbootconfig.controller;

import com.nile.springbootconfig.Config.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    DBConfig dbConfig;

    @GetMapping("/hello")
    public String hello(){
        System.out.println(dbConfig.getUsers());
        return dbConfig.getHost() + dbConfig.getPort() + dbConfig.getUsers();
    }
}
