package com.nile.springdemo.service;

import com.nile.springdemo.DTO.LoginRequest;
import com.nile.springdemo.exception.UserException;
import com.nile.springdemo.model.User;
import com.nile.springdemo.repository.UserRepository;
import com.nile.springdemo.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    public User createUser(User user) throws UserException {
        System.out.println(user);
        if(user == null){
            throw UserException.userNull();
        }
        if(Objects.isNull(user.getEmail())){
            throw UserException.emailNull();
        }
        if(Objects.isNull(user.getPassword())){
            throw UserException.passwordNull();
        }

        if(userRepository.existsByEmail(user.getEmail())){
            throw UserException.emailDuplicate();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String loginUser(LoginRequest loginRequest) throws UserException {
       Optional<User> opt =  userRepository.findByEmail(loginRequest.getEmail());
       if(opt.isEmpty()){
           throw UserException.loginEmailNotFound();
       }
       User user = opt.get();
       if(!matchPassword(loginRequest.getPassword() , user.getPassword())){
           throw UserException.loginPasswordNotMatch();
       }

       // create token
       String token = tokenService.tokenize(user);
       return token;
    }

    public boolean matchPassword(String rawPassword , String encodedPassword){
        return passwordEncoder.matches(rawPassword , encodedPassword);
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public User updateUserName(String id , String name) throws UserException {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isEmpty()){
            throw UserException.userNotFound();
        }

        User user = opt.get();
        user.setName(name);

        return userRepository.save(user);
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }
//
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public String refreshToken() throws UserException {
        Optional<String> opt =  SecurityUtil.getCurrentUserId();
        if(opt.isEmpty()){
            throw UserException.unAuthorized();
        }
        String userId = opt.get();


        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isEmpty()) {
            throw UserException.userNotFound();
        }
        User user = optUser.get();
        return tokenService.tokenize(user);
    }

}
