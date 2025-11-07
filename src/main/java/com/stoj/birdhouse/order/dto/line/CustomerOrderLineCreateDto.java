package com.stoj.birdhouse.order.dto.line;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class CustomerOrderLineCreateDto {

    @NotNull
    private Long orderId;

    @NotNull
    private Long inventoryPartId;

    @NotNull
    @PositiveOrZero
    private int quantity;

    @DecimalMin(value = "0.00", message = "Discount must be greater than or equal to zero")
    private BigDecimal discount;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getInventoryPartId() {
        return inventoryPartId;
    }

    public void setInventoryPartId(Long inventoryPartId) {
        this.inventoryPartId = inventoryPartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
