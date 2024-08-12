package com.udemy_security.service;

import com.udemy_security.entity.Customer;
import com.udemy_security.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public String createNewCustomer(Customer customer) {
        String hashedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(hashedPassword);
        try {
            customerRepository.save(customer);
        }catch (Exception e) {
            throw new UsernameNotFoundException("Username not found");
        }
        return "success";
    }
}
