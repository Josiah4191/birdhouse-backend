package com.stoj.birdhouse.customer.controller;

import com.stoj.birdhouse.customer.dto.CustomerCreateDto;
import com.stoj.birdhouse.customer.dto.CustomerResponseDto;
import com.stoj.birdhouse.customer.dto.CustomerUpdateDto;
import com.stoj.birdhouse.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // get all customers
    @GetMapping("/customers")
    public ResponseEntity<Page<CustomerResponseDto>> getAllCustomers(@ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(customerService.getAllCustomers(pageable));
    }

    // get customer by id
    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    // create customer
    @PostMapping("/customers")
    public ResponseEntity<CustomerResponseDto> createCustomer(@Valid @RequestBody CustomerCreateDto createDto) {
        CustomerResponseDto responseDto = customerService.createCustomer(createDto);
        return ResponseEntity.created(URI.create(String.format("/customers/%d", responseDto.getId()))).body(responseDto);
    }

    // update customer
    @PatchMapping("/customers/{id}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerUpdateDto updateDto) {
        CustomerResponseDto responseDto = customerService.updateCustomer(id, updateDto);
        return ResponseEntity.ok(responseDto);
    }

    // delete customer
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
