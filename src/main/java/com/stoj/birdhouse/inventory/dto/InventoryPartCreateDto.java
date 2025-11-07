package com.stoj.birdhouse.inventory.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class InventoryPartCreateDto {

    @NotBlank
    @Size(max = 50, message = "Part number must be less than 50 characters")
    private String partNumber;

    @NotBlank
    @Size(max = 255, message = "Description must be less than 200 characters")
    private String description;

    @NotNull(message = "Stock price must be provided")
    @Digits(integer = 19, fraction = 2, message = "Stock price must be a number")
    private BigDecimal stockPrice;

    @Digits(integer = 19, fraction = 2, message = "Weight must be a number")
    private BigDecimal weight;

    @NotNull(message = "Stock level must be provided")
    @Min(value = 0, message = "Stock level must be greater than or equal to 0")
    private Integer stockLevel;

    @NotNull(message = "Reorder level must be provided")
    @Min(value = 0, message = "Reorder level must be greater than or equal to 0")
    private Integer reorderLevel;

    private Integer stockOnOrder;

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

    public Integer getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(Integer stockLevel) {
        this.stockLevel = stockLevel;
    }

    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public Integer getStockOnOrder() {
        return stockOnOrder;
    }

    public void setStockOnOrder(Integer stockOnOrder) {
        this.stockOnOrder = stockOnOrder;
    }
}
