package com.stoj.birdhouse.order.dto.order;

import com.stoj.birdhouse.order.entity.OrderStatus;
import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDate;

public class CustomerOrderUpdateDto {

    private OrderStatus orderStatus;

    @FutureOrPresent
    private LocalDate requiredDate;

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDate getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(LocalDate requiredDate) {
        this.requiredDate = requiredDate;
    }

}
