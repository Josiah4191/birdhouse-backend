package com.stoj.birdhouse.order.dto.order;

import com.stoj.birdhouse.order.entity.OrderStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class CustomerOrderCreateDto {

    @PastOrPresent
    private LocalDate orderDate;

    @FutureOrPresent
    private LocalDate requiredDate;

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
}
