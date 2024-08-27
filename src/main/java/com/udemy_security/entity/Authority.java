package com.udemy_security.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author nnkipkorir
 * created 23/08/2024
 */

@Entity
@Data
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
}
