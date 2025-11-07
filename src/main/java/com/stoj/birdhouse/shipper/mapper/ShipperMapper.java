package com.stoj.birdhouse.shipper.mapper;

import com.stoj.birdhouse.shipper.dto.ShipperCreateDto;
import com.stoj.birdhouse.shipper.dto.ShipperResponseDto;
import com.stoj.birdhouse.shipper.dto.ShipperSummaryDto;
import com.stoj.birdhouse.shipper.dto.ShipperUpdateDto;
import com.stoj.birdhouse.shipper.entity.Shipper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ShipperMapper {
    ShipperResponseDto toDto(Shipper shipper);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Shipper toEntity(ShipperCreateDto shipperCreateDto);

    void updateEntityFromDto(ShipperUpdateDto shipperUpdateDto, @MappingTarget Shipper shipper);

    ShipperSummaryDto toSummaryDto(Shipper shipper);
}
