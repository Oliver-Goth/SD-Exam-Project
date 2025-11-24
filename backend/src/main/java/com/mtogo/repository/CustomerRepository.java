package com.mtogo.repository;

import com.mtogo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Example custom queries
    Customer findByEmail(String email);
    boolean existsByPhone(String phone);
}
