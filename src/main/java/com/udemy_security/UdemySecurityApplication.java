package com.udemy_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity(debug = true) //todo: if you want to see how filter work ( do not use on prod)
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)// todo to activate method level security
public class UdemySecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdemySecurityApplication.class, args);
	}

}
