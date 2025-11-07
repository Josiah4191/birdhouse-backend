package com.stoj.birdhouse.order.mapper;

import com.stoj.birdhouse.customer.mapper.CustomerMapper;
import com.stoj.birdhouse.order.dto.order.CustomerOrderCreateDto;
import com.stoj.birdhouse.order.dto.order.CustomerOrderDetailDto;
import com.stoj.birdhouse.order.dto.order.CustomerOrderResponseDto;
import com.stoj.birdhouse.order.dto.order.CustomerOrderUpdateDto;
import com.stoj.birdhouse.order.entity.CustomerOrder;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class, CustomerOrderLineMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerOrderMapper {
    CustomerOrderResponseDto toDto(CustomerOrder customerOrder);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CustomerOrder toEntity(CustomerOrderCreateDto customerOrderCreateDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(CustomerOrderUpdateDto customerOrderUpdateDto, @MappingTarget CustomerOrder customerOrder);

    CustomerOrderDetailDto toDetailDto(CustomerOrder customerOrder);
}
