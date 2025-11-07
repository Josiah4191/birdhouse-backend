package com.stoj.birdhouse.order.dto.order;

import com.stoj.birdhouse.customer.dto.CustomerSummaryDto;
import com.stoj.birdhouse.order.entity.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomerOrderResponseDto {

    private Long id;
    private OrderStatus orderStatus;
    private LocalDate orderDate;
    private LocalDate requiredDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private CustomerSummaryDto customer;

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
}
