package com.stoj.birdhouse.inventory.service;

import com.stoj.birdhouse.common.exception.NotFoundException;
import com.stoj.birdhouse.inventory.dto.InventoryPartCreateDto;
import com.stoj.birdhouse.inventory.dto.InventoryPartResponseDto;
import com.stoj.birdhouse.inventory.dto.InventoryPartUpdateDto;
import com.stoj.birdhouse.inventory.entity.InventoryPart;
import com.stoj.birdhouse.inventory.mapper.InventoryPartMapper;
import com.stoj.birdhouse.inventory.repository.InventoryPartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InventoryPartService {
    private final InventoryPartRepository inventoryPartRepo;
    private final InventoryPartMapper mapper;

    @Autowired
    public InventoryPartService(InventoryPartMapper mapper, InventoryPartRepository inventoryPartRepo) {
        this.inventoryPartRepo = inventoryPartRepo;
        this.mapper = mapper;
    }

    // get all inventory parts
    public Page<InventoryPartResponseDto> getAllInventoryParts(Pageable pageable) {
        return inventoryPartRepo.findAll(pageable).map(mapper::toDto);
    }

    // get inventory part by id
    public InventoryPartResponseDto getInventoryPart(Long id) {
        return inventoryPartRepo.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Inventory part not found"));
    }

    // create inventory part
    @Transactional
    public InventoryPartResponseDto createInventoryPart(InventoryPartCreateDto createDto) {
        InventoryPart inventoryPart = mapper.toEntity(createDto);
        inventoryPart = inventoryPartRepo.save(inventoryPart);
        return mapper.toDto(inventoryPart);
    }

    // update inventory part
    @Transactional
    public InventoryPartResponseDto updateInventoryPart(Long id, InventoryPartUpdateDto updateDto) {
        InventoryPart inventoryPart = inventoryPartRepo.findById(id).orElseThrow(() -> new NotFoundException("Inventory part not found"));
        mapper.updateEntityFromDto(updateDto, inventoryPart);
        return mapper.toDto(inventoryPart);
    }

    // delete inventory part
    @Transactional
    public void deleteInventoryPart(Long id) {
        inventoryPartRepo.deleteById(id);
    }
}
