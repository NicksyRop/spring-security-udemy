package com.udemy_security.config;

import com.udemy_security.exceptions.CustomAccessDeniedEntryPoint;
import com.udemy_security.exceptions.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("prod")
public class ProdSecurityConfig {
    // See SpringBootWebSecurityConfiguration.class for default filter implementation
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //todo: use any request to block or allow all ( authenticated )
        // http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
        // http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        //todo: use request matchers to group and protect(authenticated) /permit all
        http.requiresChannel(rcc -> rcc.anyRequest().requiresSecure()) //only process https requests alone
                .csrf(csrfConfig -> csrfConfig.disable())  //disable csrf verification
                .authorizeHttpRequests((requests) -> requests.requestMatchers(
                "/myAccount","/myLoans","/myCards","/myBalance").authenticated()
                .requestMatchers("/contact","/notices","/error" ,"/register", "/compromised").permitAll());

        //todo: disable form login and basic auth
        //http.formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.disable());
        //http.httpBasic(httpSecurityHttpBasicConfigurer ->  httpSecurityHttpBasicConfigurer.disable());
        http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint())); //todo: withDefaults() throw the default error message , inject own custom error DOES NOT WORK WITH 403 use global
        http.exceptionHandling(ehg -> ehg.accessDeniedHandler(new CustomAccessDeniedEntryPoint()));
        http.formLogin(withDefaults());
        return http.build();
    }

    /**
     * In memory database
     * @return
     */
//    @Bean
//    public UserDetailsService userDetailsService() {
//        //create a user details objects with role read
//        // prefix passwords with {noop} example "{noop}6c752f81-e876-467e"  if you have not configured Password encoder otherwise you will get server error
//        // use https://bcrypt-generator.com/ to encrypt passwords instead of  having them as plain text
//        // you can also do passwordEncoder().encode("password") instead of the above website but password will be visible on source code
//       UserDetails user =  User.withUsername("user").password( "{bcrypt}$2a$12$L3UpHxxh7bieT5WVaApOX.86s.Imn4bUVdNcQLPxVvgQcGsH61VMi").authorities("read").build();
//       UserDetails admin =  User.withUsername("admin").password("{bcrypt}$2a$12$coaTQdyZCbwjwUyMfNfKKOVpV6W4usD564K9tMgfmCtzHI9KvXlBm").authorities("admin").build();
//       // return InMemoryUserDetailsManger (implementation of userDetailsManager) since we're storing users in memory
//       return new InMemoryUserDetailsManager(user, admin);
//    }

//    /**
//     * Loads users from db
//     * @param dataSource
//     * @return
//     */
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // use this factory so spring can choose with mechanism to use to hash passwords (spring recommends bcrypt)
        // so in case spring changes the recommended hashing we don't have to update our code i.e  return new BCryptPasswordEncoder();
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * checks if password is compromised or not
     * @return
     */
    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker(){
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
