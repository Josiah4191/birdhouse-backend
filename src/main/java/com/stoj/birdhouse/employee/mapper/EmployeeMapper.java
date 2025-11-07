package com.stoj.birdhouse.employee.mapper;

import com.stoj.birdhouse.employee.dto.EmployeeCreateDto;
import com.stoj.birdhouse.employee.dto.EmployeeResponseDto;
import com.stoj.birdhouse.employee.dto.EmployeeUpdateDto;
import com.stoj.birdhouse.employee.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper {
    EmployeeResponseDto toDto(Employee employee);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Employee toEntity(EmployeeCreateDto employeeCreateDto);

    void updateEntityFromDto(EmployeeUpdateDto employeeUpdateDto, @MappingTarget Employee employee);
}
