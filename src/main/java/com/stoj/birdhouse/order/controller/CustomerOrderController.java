package com.stoj.birdhouse.order.controller;

import com.stoj.birdhouse.order.dto.order.CustomerOrderCreateDto;
import com.stoj.birdhouse.order.dto.order.CustomerOrderResponseDto;
import com.stoj.birdhouse.order.dto.order.CustomerOrderUpdateDto;
import com.stoj.birdhouse.order.service.CustomerOrderService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class CustomerOrderController {

    private final CustomerOrderService customerOrderService;

    @Autowired
    public CustomerOrderController(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    // get all customer orders
    @GetMapping("/customer-orders")
    public ResponseEntity<Page<CustomerOrderResponseDto>> getAllCustomerOrders(@ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        Page<CustomerOrderResponseDto> orders = customerOrderService.getAllCustomerOrders(pageable);
        return ResponseEntity.ok(orders);
    }

    // get customer order by id
    @GetMapping("/customer-orders/{id}")
    public ResponseEntity<CustomerOrderResponseDto> getCustomerOrder(@PathVariable Long id) {
        CustomerOrderResponseDto order = customerOrderService.getCustomerOrder(id);
        return ResponseEntity.ok(order);
    }

    // get customer orders by customer id
    @GetMapping("/customer-orders/customer/{id}")
    public ResponseEntity<Page<CustomerOrderResponseDto>> getCustomerOrderByCustomerId(@PathVariable Long id, @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        Page<CustomerOrderResponseDto> orders = customerOrderService.getCustomerOrderByCustomerId(id, pageable);
        return ResponseEntity.ok(orders);
    }

    // create customer order
    @PostMapping("/customer-orders/customer/{customerId}")
    public ResponseEntity<CustomerOrderResponseDto> createCustomerOrder(@PathVariable Long customerId, @Valid @RequestBody CustomerOrderCreateDto createDto) {
        CustomerOrderResponseDto order = customerOrderService.createCustomerOrder(customerId, createDto);
        return ResponseEntity.created(URI.create(String.format("/customer-orders/%d", order.getId()))).body(order);
    }

    // update customer order by id
    @PatchMapping("/customer-orders/{id}")
    public ResponseEntity<CustomerOrderResponseDto> updateCustomerOrder(@PathVariable Long id, @Valid @RequestBody CustomerOrderUpdateDto updateDto) {
        CustomerOrderResponseDto order = customerOrderService.updateCustomerOrder(id, updateDto);
        return ResponseEntity.ok(order);
    }

    // delete customer order by id
    @DeleteMapping("/customer-orders/{id}")
    public ResponseEntity<Void> deleteCustomerOrder(@PathVariable Long id) {
        customerOrderService.deleteCustomerOrder(id);
        return ResponseEntity.noContent().build();
    }
}
