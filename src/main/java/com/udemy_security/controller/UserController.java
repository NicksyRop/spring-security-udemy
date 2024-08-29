package com.udemy_security.controller;

import com.udemy_security.dto.CustomerRequestDto;
import com.udemy_security.entity.Customer;
import com.udemy_security.repository.CustomerRepository;
import com.udemy_security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final CustomerRepository customerRepository;


    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody CustomerRequestDto customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createNewCustomer(customer));
    }

    @GetMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(authentication.getName());
        return optionalCustomer.orElse(null);
    }

    @GetMapping("/compromised")
    public ResponseEntity<Boolean> getCompromisedUsers(@RequestParam String password) {
        return ResponseEntity.ok().body(userService.isCompromisedPassword(password));
    }
}
