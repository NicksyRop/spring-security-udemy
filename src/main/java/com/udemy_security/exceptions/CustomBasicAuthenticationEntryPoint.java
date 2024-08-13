package com.udemy_security.exceptions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author nnkipkorir
 * created 13/08/2024
 */
// implement AuthenticationEntryPoint for 401 and AccessDeniedHandler for 403
public class CustomBasicAuthenticationEntryPoint implements AuthenticationEntryPoint {
    //todo : copy the logic from one of the implementation of AuthenticationEntryPoint and edit
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        LocalDateTime timestamp = LocalDateTime.now();
        String path = request.getRequestURI();
        String message = (authException != null && authException.getMessage() != null) ?  authException.getMessage() : "Unauthorized";
        response.setHeader("Custom-header", "Authentication Failed");
       // response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase()); //todo : replace with .setStatus  spring womt invoke custom json
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json; charset=utf-8");
        String json = String.format("{\"timestamp\": \"%s\", \"status\": %d, \"error\": \"%s\", \"message\": \"%s\", \"path\": \"%s\"}",
                timestamp, HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), message, path);
        response.getWriter().write(json);

    }

}
