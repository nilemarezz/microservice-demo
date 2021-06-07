package com.nile.springdemo.repository;

import com.nile.springdemo.model.Address;
import com.nile.springdemo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address , String> {
    List<Address> findByUser(User user);
}
