package com.stoj.birdhouse.order.entity;

import com.stoj.birdhouse.inventory.entity.InventoryPart;
import jakarta.persistence.*;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_order_line", uniqueConstraints = @UniqueConstraint(columnNames = {"order_id", "part_number"}))
public class CustomerOrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_line_id")
    private Long id;

    @Column(name = "quantity", nullable = false)
    @Check(constraints = "quantity >= 0")
    private Integer quantity;

    @Column(name = "unit_price", precision = 19, scale = 2, nullable = false)
    @Check(constraints = "unit_price >= 0")
    private BigDecimal unitPrice;

    @Column(name = "discount", precision = 19, scale = 2)
    @Check(constraints = "discount >= 0 and discount <= unit_price")
    private BigDecimal discount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private CustomerOrder customerOrder;

    @ManyToOne
    @JoinColumn(name = "inventory_part_id", nullable = false)
    private InventoryPart inventoryPart;

    public CustomerOrderLine() {}

    public Long getId() {
        return id;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public InventoryPart getInventoryPart() {
        return inventoryPart;
    }

    public void setInventoryPart(InventoryPart inventoryPart) {
        this.inventoryPart = inventoryPart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
