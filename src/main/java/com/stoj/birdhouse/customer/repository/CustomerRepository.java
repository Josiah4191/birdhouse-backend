package com.stoj.birdhouse.customer.repository;

import com.stoj.birdhouse.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}
