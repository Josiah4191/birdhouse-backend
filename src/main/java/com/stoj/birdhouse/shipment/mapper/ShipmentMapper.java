package com.stoj.birdhouse.shipment.mapper;

import com.stoj.birdhouse.order.mapper.CustomerOrderMapper;
import com.stoj.birdhouse.shipment.dto.ShipmentCreateDto;
import com.stoj.birdhouse.shipment.dto.ShipmentDetailDto;
import com.stoj.birdhouse.shipment.dto.ShipmentSummaryDto;
import com.stoj.birdhouse.shipment.dto.ShipmentUpdateDto;
import com.stoj.birdhouse.shipment.entity.Shipment;
import com.stoj.birdhouse.shipper.mapper.ShipperMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {ShipperMapper.class, CustomerOrderMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ShipmentMapper {
    ShipmentDetailDto toDetailDto(Shipment shipment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Shipment toEntity(ShipmentCreateDto createDto);

    ShipmentSummaryDto  toSummaryDto(Shipment shipment);

    void updateEntityFromDto(ShipmentUpdateDto updateDto, @MappingTarget Shipment shipment);

}
