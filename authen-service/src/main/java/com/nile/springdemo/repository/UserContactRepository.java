package com.nile.springdemo.repository;

import com.nile.springdemo.model.User;
import com.nile.springdemo.model.UserContact;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserContactRepository extends CrudRepository<UserContact , String> {

    Optional<UserContact> findByUser(User user);
}
