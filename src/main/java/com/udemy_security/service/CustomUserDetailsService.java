package com.udemy_security.service;

import com.udemy_security.entity.Customer;
import com.udemy_security.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Custom implementation of user details service , fetch user from own defined database tables and columns
 * so  authentication provider calls the loadUserByUsername method in this service
 */

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private  final CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Customer customer =  customerRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("User details  not found , with username: " + username));
       List<GrantedAuthority> grantedAuthorities = List.of( new SimpleGrantedAuthority(customer.getRole()));
       //create user object from the customer
        return new User(customer.getEmail(), customer.getPassword(), grantedAuthorities);
    }
}
