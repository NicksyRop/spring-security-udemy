package com.udemy_security.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author nnkipkorir
 * created 29/08/2024
 */

@Component
@ConfigurationProperties(prefix = "security")
@Data
public class JwtConfiguration {
    private  Jtw jwt;

    @Data
    public static class Jtw {
        private String secretKey;
    }
}
