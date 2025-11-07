package com.stoj.birdhouse.shipper.controller;

import com.stoj.birdhouse.shipper.dto.ShipperCreateDto;
import com.stoj.birdhouse.shipper.dto.ShipperResponseDto;
import com.stoj.birdhouse.shipper.dto.ShipperUpdateDto;
import com.stoj.birdhouse.shipper.service.ShipperService;
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
public class ShipperController {

    private final ShipperService shipperService;

    @Autowired
    public ShipperController(ShipperService shipperService) {
        this.shipperService = shipperService;
    }

    // get all shippers
    @GetMapping("/shippers")
    public ResponseEntity<Page<ShipperResponseDto>> getAllShippers(@ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        Page<ShipperResponseDto> shippers = shipperService.getAllShippers(pageable);
        return ResponseEntity.ok(shippers);
    }

    // get one shipper by id
    @GetMapping("/shippers/{id}")
    public ResponseEntity<ShipperResponseDto> getShipper(@PathVariable Long id) {
        ShipperResponseDto shipper = shipperService.getShipper(id);
        return ResponseEntity.ok(shipper);
    }

    // create a new shipper
    @PostMapping("/shippers")
    public ResponseEntity<ShipperResponseDto> createShipper(@Valid @RequestBody ShipperCreateDto createDto) {
        ShipperResponseDto shipper = shipperService.createShipper(createDto);
        return ResponseEntity.created(URI.create(String.format("/shippers/%d", shipper.getId()))).body(shipper);
    }

    // update existing shipper
    @PatchMapping("/shippers/{id}")
    public ResponseEntity<ShipperResponseDto> updateShipper(@PathVariable Long id, @Valid @RequestBody ShipperUpdateDto updateDto) {
        ShipperResponseDto shipper = shipperService.updateShipper(id, updateDto);
        return ResponseEntity.ok(shipper);
    }

    // delete existing shipper
    @DeleteMapping("/shippers/{id}")
    public void deleteShipper(@PathVariable Long id) {
        shipperService.deleteShipper(id);
    }

}
