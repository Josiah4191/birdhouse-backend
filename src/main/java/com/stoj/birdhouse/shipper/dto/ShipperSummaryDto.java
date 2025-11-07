package com.stoj.birdhouse.shipper.dto;

public class ShipperSummaryDto {
    private Long id;
    private String shipperName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }
}
