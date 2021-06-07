package com.nile.springdemo.service;

import com.nile.springdemo.model.User;
import com.nile.springdemo.model.UserContact;
import com.nile.springdemo.repository.UserContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserContactService {
    @Autowired
    UserContactRepository userContactRepository;

    public Optional<UserContact> findByUser(User user){
        return userContactRepository.findByUser(user);
    }

    public UserContact createContact(User user ,UserContact userContact){
        userContact.setUser(user);
        return userContactRepository.save(userContact);
    }
}
