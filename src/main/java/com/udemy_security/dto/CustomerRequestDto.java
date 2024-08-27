package com.udemy_security.dto;

import lombok.Data;

import java.util.Set;

/**
 * @author nnkipkorir
 * created 26/08/2024
 */

@Data
public class CustomerRequestDto {
    private String email;
    private String password;
    private String role;
    private Set<AuthorityDto> authorities;
}
