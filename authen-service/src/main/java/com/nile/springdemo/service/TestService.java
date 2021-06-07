package com.nile.springdemo.service;

import com.nile.springdemo.exception.BaseException;
import com.nile.springdemo.exception.UserException;
import com.nile.springdemo.model.User;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TestService {
    public String register(User user) throws BaseException {
        if(user == null){
            throw UserException.userNull();
        }

        if(Objects.isNull(user.getEmail())){
            throw UserException.emailNull();
        }


        return "user" + user;
    }
}
