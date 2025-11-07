package com.stoj.birdhouse.shipment.controller;

import com.stoj.birdhouse.shipment.dto.ShipmentCreateDto;
import com.stoj.birdhouse.shipment.dto.ShipmentDetailDto;
import com.stoj.birdhouse.shipment.dto.ShipmentSummaryDto;
import com.stoj.birdhouse.shipment.dto.ShipmentUpdateDto;
import com.stoj.birdhouse.shipment.service.ShipmentService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class ShipmentController {

    private ShipmentService shipmentService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    // get all shipments
    @GetMapping("/shipments")
    public ResponseEntity<Page<ShipmentSummaryDto>> getAllShipments(@ParameterObject @PageableDefault(size=20) Pageable pageable) {
        var shipments = shipmentService.getAllShipments(pageable);
        return ResponseEntity.ok(shipments);
    }

    // get shipment by id
    @GetMapping("/shipments/{id}")
    public ResponseEntity<ShipmentDetailDto> getShipment(Long id) {
        var shipment = shipmentService.getShipment(id);
        return ResponseEntity.ok(shipment);
    }

    // create shipment
    @PostMapping("/shipments/orders/{orderId}/shippers/{shipperId}")
    public ResponseEntity<ShipmentDetailDto> createShipment(@PathVariable Long orderId, @PathVariable Long shipperId, @Valid @RequestBody ShipmentCreateDto createDto) {
        var shipment = shipmentService.createShipment(orderId, shipperId, createDto);
        return ResponseEntity.created(URI.create(String.format("/shipments/%d", shipment.getId()))).body(shipment);
    }

    // update shipment
    @PatchMapping("/shipments/{id}")
    public ResponseEntity<ShipmentDetailDto> updateShipment(@PathVariable Long id, @Valid @RequestBody ShipmentUpdateDto updateDto) {
        var shipment = shipmentService.updateShipment(id, updateDto);
        return ResponseEntity.ok(shipment);
    }

    // delete shipment
    @DeleteMapping("/shipments/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Long id) {
        shipmentService.deleteShipment(id);
        return ResponseEntity.noContent().build();
    }
}
