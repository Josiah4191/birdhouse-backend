package com.stoj.birdhouse.inventory.controller;

import com.stoj.birdhouse.inventory.dto.InventoryPartCreateDto;
import com.stoj.birdhouse.inventory.dto.InventoryPartResponseDto;
import com.stoj.birdhouse.inventory.dto.InventoryPartUpdateDto;
import com.stoj.birdhouse.inventory.service.InventoryPartService;
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
public class InventoryPartController {

    private final InventoryPartService inventoryPartService;

    @Autowired
    public InventoryPartController(InventoryPartService inventoryPartService) {
        this.inventoryPartService = inventoryPartService;
    }

    // get all inventory parts
    @GetMapping("/parts")
    public ResponseEntity<Page<InventoryPartResponseDto>> getAllInventoryParts(@ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(inventoryPartService.getAllInventoryParts(pageable));
    }

    // get inventory part by id
    @GetMapping("/parts/{id}")
    public ResponseEntity<InventoryPartResponseDto> getInventoryPart(@PathVariable Long id) {
        return ResponseEntity.ok(inventoryPartService.getInventoryPart(id));
    }

    // create inventory part
    @PostMapping("/parts")
    public ResponseEntity<InventoryPartResponseDto> createInventoryPart(@Valid @RequestBody InventoryPartCreateDto createDto) {
        InventoryPartResponseDto responseDto = inventoryPartService.createInventoryPart(createDto);
        return ResponseEntity.created(URI.create(String.format("/users/%d", responseDto.getId()))).body(responseDto);
    }

    // update inventory part
    @PatchMapping("/parts/{id}")
    public ResponseEntity<InventoryPartResponseDto> updateInventoryPart(@PathVariable Long id, @Valid @RequestBody InventoryPartUpdateDto updateDto) {
        InventoryPartResponseDto responseDto = inventoryPartService.updateInventoryPart(id, updateDto);
        return ResponseEntity.ok(responseDto);
    }

    // delete inventory part
    @DeleteMapping("/parts/{id}")
    public void deleteInventoryPart(@PathVariable Long id) {
        inventoryPartService.deleteInventoryPart(id);
    }
}
