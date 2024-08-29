package com.udemy_security.config.filter;

import com.udemy_security.constants.ApplicationConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author nnkipkorir
 * created 29/08/2024
 */

public class JWTTokenValidatorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader(ApplicationConstants.JWT_HEADER);
        if(jwt != null) {
            try{


            }catch (Exception e) {
                throw new BadCredentialsException("Invalid JWT token");
            }
        }

        //invoke next filter
        filterChain.doFilter(request, response);
    }

    //logic to check when to invoke filter
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/user"); // endpoint for login
    }

}
