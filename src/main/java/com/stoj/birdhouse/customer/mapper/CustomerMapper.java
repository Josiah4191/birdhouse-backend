package com.stoj.birdhouse.customer.mapper;

import com.stoj.birdhouse.customer.dto.CustomerCreateDto;
import com.stoj.birdhouse.customer.dto.CustomerResponseDto;
import com.stoj.birdhouse.customer.dto.CustomerSummaryDto;
import com.stoj.birdhouse.customer.dto.CustomerUpdateDto;
import com.stoj.birdhouse.customer.entity.Customer;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {
    CustomerResponseDto toDto(Customer customer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Customer toEntity(CustomerCreateDto customerCreateDto);

    void updateEntityFromDto(CustomerUpdateDto customerUpdateDto, @MappingTarget Customer customer);

    CustomerSummaryDto toSummaryDto(Customer customer);
}
