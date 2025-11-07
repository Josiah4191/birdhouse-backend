package com.stoj.birdhouse.shipper.service;

import com.stoj.birdhouse.common.exception.NotFoundException;
import com.stoj.birdhouse.shipper.dto.ShipperCreateDto;
import com.stoj.birdhouse.shipper.dto.ShipperResponseDto;
import com.stoj.birdhouse.shipper.dto.ShipperUpdateDto;
import com.stoj.birdhouse.shipper.entity.Shipper;
import com.stoj.birdhouse.shipper.mapper.ShipperMapper;
import com.stoj.birdhouse.shipper.repository.ShipperRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ShipperService {

    private final ShipperRepository shipperRepo;
    private final ShipperMapper mapper;

    @Autowired
    public ShipperService(ShipperMapper mapper, ShipperRepository shipperRepo) {
        this.shipperRepo = shipperRepo;
        this.mapper = mapper;
    }

    // get all shippers
    public Page<ShipperResponseDto> getAllShippers(Pageable pageable) {
        return shipperRepo.findAll(pageable).map(mapper::toDto);
    }

    // get shipper by id
    public ShipperResponseDto getShipper(Long id) {
        return shipperRepo.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new NotFoundException("Shipper not found"));
    }

    // create shipper
    @Transactional
    public ShipperResponseDto createShipper(ShipperCreateDto createDto) {
        Shipper shipper = mapper.toEntity(createDto);
        shipper = shipperRepo.save(shipper);
        return mapper.toDto(shipper);
    }

    // update shipper
    @Transactional
    public ShipperResponseDto updateShipper(Long id, ShipperUpdateDto updateDto) {
        Shipper shipper = shipperRepo.findById(id).orElseThrow(() -> new NotFoundException("Shipper not found."));
        mapper.updateEntityFromDto(updateDto, shipper);
        return mapper.toDto(shipper);
    }

    // delete shipper
    @Transactional
    public void deleteShipper(Long id) {
        shipperRepo.deleteById(id);
    }
}
