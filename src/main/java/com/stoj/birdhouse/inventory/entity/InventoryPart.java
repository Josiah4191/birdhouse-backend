package com.stoj.birdhouse.inventory.entity;

import com.stoj.birdhouse.order.entity.CustomerOrderLine;
import jakarta.persistence.*;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "inventory_part")
public class InventoryPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_part_id")
    private Long id;

    @Column(name = "part_number", length = 50, nullable = false, unique = true)
    private String partNumber;

    @Column(name = "description", length = 255, nullable = false)
    private String description;

    @Column(name = "stock_price", precision = 19, scale = 2, nullable = false)
    @Check(constraints = "stock_price >= 0")
    private BigDecimal stockPrice;

    @Column(name = "weight", precision = 10, scale = 2)
    @Check(constraints = "weight >= 0")
    private BigDecimal weight;

    @Column(name = "stock_level", nullable = false)
    @Check(constraints = "stock_level >= 0")
    private Integer stockLevel;

    @Column(name = "reorder_level", nullable = false)
    @Check(constraints = "reorder_level >= 0")
    private Integer reorderLevel;

    @Column(name = "stock_on_order")
    private Integer stockOnOrder;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "inventoryPart")
    private List<CustomerOrderLine> customerOrderLines;

    public InventoryPart() {}

    public Long getId() {
        return id;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(BigDecimal stockPrice) {
        this.stockPrice = stockPrice;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public int getStockOnOrder() {
        return stockOnOrder;
    }

    public void setStockOnOrder(int stockOnOrder) {
        this.stockOnOrder = stockOnOrder;
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
