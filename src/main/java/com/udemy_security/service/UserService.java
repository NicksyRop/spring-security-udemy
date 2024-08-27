package com.udemy_security.service;

import com.udemy_security.dto.AuthorityDto;
import com.udemy_security.dto.CustomerRequestDto;
import com.udemy_security.entity.Authority;
import com.udemy_security.entity.Customer;
import com.udemy_security.repository.AuthorityRepository;
import com.udemy_security.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final CustomerRepository customerRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompromisedPasswordChecker compromisedPasswordChecker;

    public String createNewCustomer(CustomerRequestDto customerRequestDto) {

        //check if password is compromised
        if(isCompromisedPassword(customerRequestDto.getPassword())) {
            throw new RuntimeException("Compromised password");
        }
        String hashedPassword = passwordEncoder.encode(customerRequestDto.getPassword());
        Customer customer = new Customer();
        customer.setPassword(hashedPassword);
        customer.setEmail(customerRequestDto.getEmail());
        customer.setRole(customerRequestDto.getRole());
        Set<Authority> newAuthorities = new HashSet<>();
        for (AuthorityDto authorityDto : customerRequestDto.getAuthorities()) {
            Optional<Authority> optionalAuthority = authorityRepository.findByName(authorityDto.getName());
            optionalAuthority.ifPresent(newAuthorities::add);
        }
        if (customer.getAuthorities() == null) {
            customer.setAuthorities(new HashSet<>());
        }
        customer.getAuthorities().addAll(newAuthorities);
        try {
            customerRepository.save(customer);
        }catch (Exception e) {
            throw new UsernameNotFoundException("Error while saving customer found");
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
