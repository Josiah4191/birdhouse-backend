package com.stoj.birdhouse.order.dto.order;

import com.stoj.birdhouse.customer.dto.CustomerSummaryDto;
import com.stoj.birdhouse.order.dto.line.CustomerOrderLineResponseDto;
import com.stoj.birdhouse.order.entity.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CustomerOrderDetailDto {
    private Long id;
    private OrderStatus orderStatus;
    private LocalDate orderDate;
    private LocalDate requiredDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private CustomerSummaryDto customer;
    private List<CustomerOrderLineResponseDto> customerOrderLines;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(LocalDate requiredDate) {
        this.requiredDate = requiredDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public CustomerSummaryDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerSummaryDto customer) {
        this.customer = customer;
    }

    public List<CustomerOrderLineResponseDto> getCustomerOrderLines() {
        return customerOrderLines;
    }

    public void setCustomerOrderLines(List<CustomerOrderLineResponseDto> customerOrderLines) {
        this.customerOrderLines = customerOrderLines;
    }
}
