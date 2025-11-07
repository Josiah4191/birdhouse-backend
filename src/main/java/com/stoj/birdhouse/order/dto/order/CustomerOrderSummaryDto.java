package com.stoj.birdhouse.order.dto.order;

import com.stoj.birdhouse.customer.dto.CustomerSummaryDto;
import com.stoj.birdhouse.order.entity.OrderStatus;

public class CustomerOrderSummaryDto {
    private Long id;
    private OrderStatus orderStatus;
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

    public CustomerSummaryDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerSummaryDto customer) {
        this.customer = customer;
    }
}
