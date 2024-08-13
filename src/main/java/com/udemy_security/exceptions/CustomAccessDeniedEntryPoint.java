package com.udemy_security.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author nnkipkorir
 * created 13/08/2024
 */
// implement AuthenticationEntryPoint for 401 and AccessDeniedHandler for 403
public class CustomAccessDeniedEntryPoint implements AccessDeniedHandler {
    //todo : copy the logic from one of the implementation of AuthenticationEntryPoint and edit

    // this should be configured at global level and not http.basic in the securityFilterChain
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException {
        LocalDateTime timestamp = LocalDateTime.now();
        String path = request.getRequestURI();
        String message = (accessDeniedException != null && accessDeniedException.getMessage() != null) ?  accessDeniedException.getMessage() : "Unauthorized";
        response.setHeader("Custom-header", "Authentication Failed");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json; charset=utf-8");
        String json = String.format("{\"timestamp\": \"%s\", \"status\": %d, \"error\": \"%s\", \"message\": \"%s\", \"path\": \"%s\"}",
                timestamp, HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), message, path);
        response.getWriter().write(json);

    }
}
