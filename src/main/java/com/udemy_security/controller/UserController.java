package com.udemy_security.controller;

import com.udemy_security.entity.Customer;
import com.udemy_security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createNewCustomer(customer));
    }
}
