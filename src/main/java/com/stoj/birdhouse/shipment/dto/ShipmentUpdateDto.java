package com.stoj.birdhouse.shipment.dto;

import com.stoj.birdhouse.shipment.entity.ShipmentStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ShipmentUpdateDto {

    @NotNull
    @Size(max = 30)
    private ShipmentStatus shipmentStatus;

    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }
}
