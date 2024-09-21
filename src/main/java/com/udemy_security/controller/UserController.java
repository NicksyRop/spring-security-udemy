package com.udemy_security.controller;

import com.udemy_security.entity.Customer;
import com.udemy_security.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    //private final UserService userService;
    private final CustomerRepository customerRepository;
   // private final AuthenticationManager authenticationManager;


//    @PostMapping("/register")
//    public ResponseEntity<String> createUser(@RequestBody CustomerRequestDto customer) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createNewCustomer(customer));
//    }

    @GetMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(authentication.getName());
        return optionalCustomer.orElse(null);
    }

//    @GetMapping("/compromised")
//    public ResponseEntity<Boolean> getCompromisedUsers(@RequestParam String password) {
//        return ResponseEntity.ok().body(userService.isCompromisedPassword(password));
//    }

//    @PostMapping("/apiLogin")
//    public ResponseEntity<LoginResponseDTO> apiLogin(@RequestBody LoginRequestDTO loginRequest) {
//        //todo manually initiate authentication
//        String jwt = null;
//        //todo create authentication object manually  and create a UsernamePasswordAuthenticationToken object with unauthenticated
//        Authentication authentication =  UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(), loginRequest.password());
//        //todo try to authenticate the user using authentication manger
//        Authentication authenticationResponse = authenticationManager.authenticate(authentication);
//        //todo check if authentication is not null , and  is successful or not
//        if (authenticationResponse != null && authenticationResponse.isAuthenticated()) {
//            //generate JWT
//            String secret =  ApplicationConstants.JWT_SECRET_VALUE;
//            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
//             jwt  = Jwts.builder().issuer("Nicksy").subject("JWT Token")
//                    .claim("username", authenticationResponse.getName())
//                    .claim("authorities", authenticationResponse.getAuthorities().stream().map( //todo get authorities from the authenticated Authentication object
//                            GrantedAuthority::getAuthority
//                    ).collect(Collectors.joining(","))) // join the authorities in comma seperated values
//                    .issuedAt(new Date())
//                    .expiration(new Date(new Date().getTime() + 1800000)) // set expiry time (miliseconds)
//                    .signWith(secretKey).compact();
//
//        }
//
//        return ResponseEntity.ok().header(ApplicationConstants.JWT_HEADER, jwt).
//                body(new LoginResponseDTO(HttpStatus.OK.value(), jwt));
//
//    }

}
