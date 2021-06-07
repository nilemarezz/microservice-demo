package com.nile.springdemo.service;

import com.nile.springdemo.exception.ProductException;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    public String getProductById(String id) throws ProductException {
        System.out.println(id == "99");
        if(id == "99"){
            throw ProductException.notFound();
        }
        return id;
    }
}
