package com.stoj.birdhouse.customer.service;

import com.stoj.birdhouse.common.exception.NotFoundException;
import com.stoj.birdhouse.customer.dto.CustomerCreateDto;
import com.stoj.birdhouse.customer.dto.CustomerResponseDto;
import com.stoj.birdhouse.customer.dto.CustomerUpdateDto;
import com.stoj.birdhouse.customer.entity.Customer;
import com.stoj.birdhouse.customer.mapper.CustomerMapper;
import com.stoj.birdhouse.customer.repository.CustomerRepository;
import com.stoj.birdhouse.order.dto.order.CustomerOrderDetailDto;
import com.stoj.birdhouse.order.entity.CustomerOrder;
import com.stoj.birdhouse.order.mapper.CustomerOrderMapper;
import com.stoj.birdhouse.order.repository.CustomerOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepo;
    private final CustomerMapper mapper;
    private final CustomerOrderMapper orderMapper;
    private final CustomerOrderRepository customerOrderRepo;

    @Autowired
    public CustomerService(CustomerMapper mapper, CustomerOrderMapper orderMapper, CustomerRepository customerRepo, CustomerOrderRepository customerOrderRepo) {
        this.customerRepo = customerRepo;
        this.mapper = mapper;
        this.orderMapper = orderMapper;
        this.customerOrderRepo = customerOrderRepo;
    }

    // get customer by id
    public CustomerResponseDto getCustomer(Long id) {
        return customerRepo.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    // get all customers
    public Page<CustomerResponseDto> getAllCustomers(Pageable pageable) {
        Page<Customer> customers = customerRepo.findAll(pageable);
        return customers.map(mapper::toDto);
    }

    // create customer
    @Transactional
    public CustomerResponseDto createCustomer(CustomerCreateDto createDto) {
        Customer customer = mapper.toEntity(createDto);
        customer = customerRepo.save(customer);
        return mapper.toDto(customer);
    }

    // update customer by id
    @Transactional
    public CustomerResponseDto updateCustomer(Long id, CustomerUpdateDto updateDto) {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));
        mapper.updateEntityFromDto(updateDto, customer);
        return mapper.toDto(customer);
    }

    // delete customer by id
    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));
        customerRepo.delete(customer);
    }

    // get a specific order for a customer
    @Transactional
    public CustomerOrderDetailDto getCustomerOrder(Long customerId, Long orderId) {
        CustomerOrder order = customerOrderRepo
                .findByCustomerIdAndId(customerId, orderId)
                .orElseThrow(() -> new NotFoundException(String.format("Customer order not found for customer id: %d", customerId)));

        return orderMapper.toDetailDto(order);
    }

}

