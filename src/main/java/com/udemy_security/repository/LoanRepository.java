package com.udemy_security.repository;

import com.udemy_security.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author nnkipkorir
 * created 28/08/2024
 */

@Repository
public interface LoanRepository extends JpaRepository<Loans, Long> {
    List<Loans> findByCustomerIdOrderByStartDtDesc(long customerId);

}
