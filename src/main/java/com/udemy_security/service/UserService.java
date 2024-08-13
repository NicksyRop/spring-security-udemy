package com.udemy_security.service;

import com.udemy_security.entity.Customer;
import com.udemy_security.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompromisedPasswordChecker compromisedPasswordChecker;

    public String createNewCustomer(Customer customer) {
        //check if password is compromised
        if(isCompromisedPassword(customer.getPassword())) {
            throw new RuntimeException("Compromised password");
        }
        String hashedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(hashedPassword);
        try {
            customerRepository.save(customer);
        }catch (Exception e) {
            throw new UsernameNotFoundException("Username not found");
        }
        return "success";
    }

    public boolean isCompromisedPassword(String password){
        CompromisedPasswordDecision check = compromisedPasswordChecker.check(password);
        boolean compromised = check.isCompromised();
        if (compromised) {
            log.info("Compromised password");
        }
        return compromised;
    }
}
