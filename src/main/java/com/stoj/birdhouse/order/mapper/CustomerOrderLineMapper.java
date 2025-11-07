package com.stoj.birdhouse.order.mapper;

import com.stoj.birdhouse.order.dto.line.CustomerOrderLineCreateDto;
import com.stoj.birdhouse.order.dto.line.CustomerOrderLineResponseDto;
import com.stoj.birdhouse.order.entity.CustomerOrderLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {CustomerOrderMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerOrderLineMapper {
    CustomerOrderLineResponseDto toDto(CustomerOrderLine customerOrderLine);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CustomerOrderLine toEntity(CustomerOrderLineCreateDto customerOrderLineCreateDto);
}
