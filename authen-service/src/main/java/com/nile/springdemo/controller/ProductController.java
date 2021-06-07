package com.nile.springdemo.controller;

import com.nile.springdemo.exception.ProductException;
import com.nile.springdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable("id") String id) throws ProductException {
        String res = productService.getProductById(id);
        return ResponseEntity.ok(res);
    }
}
