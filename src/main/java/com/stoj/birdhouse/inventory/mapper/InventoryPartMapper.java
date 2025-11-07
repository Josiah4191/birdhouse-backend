package com.stoj.birdhouse.inventory.mapper;

import com.stoj.birdhouse.inventory.dto.InventoryPartCreateDto;
import com.stoj.birdhouse.inventory.dto.InventoryPartResponseDto;
import com.stoj.birdhouse.inventory.dto.InventoryPartUpdateDto;
import com.stoj.birdhouse.inventory.entity.InventoryPart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
public interface InventoryPartMapper {

    InventoryPartResponseDto toDto(InventoryPart inventoryPart);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    InventoryPart toEntity(InventoryPartCreateDto inventoryPartCreateDto);

    void updateEntityFromDto(InventoryPartUpdateDto inventoryPartUpdateDto, @MappingTarget InventoryPart inventoryPart);


}
