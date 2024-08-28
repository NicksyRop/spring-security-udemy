package com.udemy_security.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Filter to do email check before BasicAuthenticationFilter
 * @author nnkipkorir
 * created 28/08/2024
 */

public class RequestValidationBeforeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //Typecast request and response
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //todo : logic only if we have authorization so we can decode to get username and password
        String header = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null) {
            header = header.trim();
            if (StringUtils.startsWithIgnoreCase(header, "Basic")) {
                byte[] bytes = header.substring(6).getBytes(StandardCharsets.UTF_8);
                byte[] decode;

                try{
                    decode = Base64.getDecoder().decode(bytes);
                    String token = new String(decode, StandardCharsets.UTF_8);
                    int delin = token.indexOf(":");
                    if (delin == -1) {
                        throw new BadCredentialsException("Invalid authentication token");
                    }

                    String email = token.substring(0, delin);
                    if(email.toLowerCase().contains("test")) {
                        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        return;
                    }
                }catch (IllegalArgumentException  e){
                    throw new IllegalArgumentException("failed to decode authentication token");
                }

            }
        }


        //todo - invoke the next filter (make sure)
        chain.doFilter(request, response);


    }
}
