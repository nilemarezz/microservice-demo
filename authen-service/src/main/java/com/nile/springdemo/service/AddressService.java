package com.nile.springdemo.service;

import com.nile.springdemo.model.Address;
import com.nile.springdemo.model.User;
import com.nile.springdemo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public List<Address> findByUser(User user){
        return addressRepository.findByUser(user);
    }

    public Address createAddress( User user , Address address){
        address.setUser(user);
        return addressRepository.save(address);
    }
}
