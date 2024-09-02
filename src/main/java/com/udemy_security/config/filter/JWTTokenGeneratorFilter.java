package com.udemy_security.config.filter;

import com.udemy_security.constants.ApplicationConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author nnkipkorir
 * created 29/08/2024
 */

public class JWTTokenGeneratorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //get details of logged in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Environment env = getEnvironment(); // able to read all the configs
            if (env != null) {
                String secret = env.getProperty(ApplicationConstants.JWT_SECRET_KEY, ApplicationConstants.JWT_SECRET_VALUE);//Read secret from configs if not found use default
                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                String jwt  = Jwts.builder().issuer("Nicksy").subject("JWT Token")
                        .claim("username", authentication.getName())
                        .claim("authorities", authentication.getAuthorities().stream().map(
                                GrantedAuthority::getAuthority
                        ).collect(Collectors.joining(","))) // join the authorities in comma seperated values
                        .issuedAt(new Date())
                        .expiration(new Date(new Date().getTime() + 1800000)) // set expiry time (miliseconds)
                        .signWith(secretKey).compact();// compact method converts method to string
                response.setHeader(ApplicationConstants.JWT_HEADER, jwt); //todo add the jwt to the header
            }
        }

        //invoke the next filter //todo IMPORTANT
        filterChain.doFilter(request, response);
    }
    //todo:  copy this method from the extended class
    //todo : tell spring when to execute this filter i.e only on login(generating JWT)  and not when validating token
    // when true , the filter is not invoked
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/user"); // endpoint for login
    }

}
