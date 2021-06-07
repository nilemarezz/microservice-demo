package com.nile.springdemo.repository;

import com.nile.springdemo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);



}
