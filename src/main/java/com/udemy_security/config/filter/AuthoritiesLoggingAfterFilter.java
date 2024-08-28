package com.udemy_security.config.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

/**
 * @author nnkipkorir
 * created 28/08/2024
 */

//todo: custom filter that will log user authorities after successful authentication
@Slf4j
public class AuthoritiesLoggingAfterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            log.info("User {}  is successfully authenticated logged in  with authorities : {}", authentication.getName()
                    , authentication.getAuthorities());
        }
       // todo: make sure you invoke next chain
        chain.doFilter(request, response);
    }
}
