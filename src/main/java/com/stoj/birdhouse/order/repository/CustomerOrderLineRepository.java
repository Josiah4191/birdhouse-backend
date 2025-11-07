package com.stoj.birdhouse.order.repository;

import com.stoj.birdhouse.order.entity.CustomerOrderLine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerOrderLineRepository extends JpaRepository<CustomerOrderLine, Long> {
    Page<CustomerOrderLine> findByCustomerOrderId(Long orderId, Pageable pageable);

    Optional<CustomerOrderLine> findByCustomerOrderIdAndId(Long orderId, Long orderLineId);

    boolean existsByCustomerOrderIdAndInventoryPartId(Long orderId, Long partId);
}
