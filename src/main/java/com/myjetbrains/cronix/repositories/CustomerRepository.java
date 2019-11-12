package com.myjetbrains.cronix.repositories;

import com.myjetbrains.cronix.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByLastname(String lastName);

    Optional<Customer> findById(Long id);
}
