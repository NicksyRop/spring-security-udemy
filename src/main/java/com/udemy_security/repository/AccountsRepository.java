package com.udemy_security.repository;

import com.udemy_security.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nnkipkorir
 * created 28/08/2024
 */

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    Accounts findByCustomerId(long customerId);
}
