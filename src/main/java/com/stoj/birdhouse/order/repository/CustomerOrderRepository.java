package com.stoj.birdhouse.order.repository;

import com.stoj.birdhouse.order.entity.CustomerOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
    Page<CustomerOrder> findByCustomerId(Long customerId, Pageable pageable);

    Optional<CustomerOrder> findByCustomerIdAndId(Long customerId, Long id);
}
