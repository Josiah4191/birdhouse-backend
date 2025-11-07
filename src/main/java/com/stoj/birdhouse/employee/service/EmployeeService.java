package com.stoj.birdhouse.employee.service;

import com.stoj.birdhouse.common.exception.NotFoundException;
import com.stoj.birdhouse.customer.entity.Customer;
import com.stoj.birdhouse.employee.dto.EmployeeCreateDto;
import com.stoj.birdhouse.employee.dto.EmployeeResponseDto;
import com.stoj.birdhouse.employee.dto.EmployeeUpdateDto;
import com.stoj.birdhouse.employee.entity.Employee;
import com.stoj.birdhouse.employee.mapper.EmployeeMapper;
import com.stoj.birdhouse.employee.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepo;
    private final EmployeeMapper mapper;

    @Autowired
    public EmployeeService(EmployeeMapper mapper, EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
        this.mapper = mapper;
    }

    // get all employees
    public Page<EmployeeResponseDto> getAllEmployees(Pageable pageable) {
        return employeeRepo.findAll(pageable).map(mapper::toDto);
    }

    // get employee by id
    public EmployeeResponseDto getEmployee(Long id) {
        return employeeRepo.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new NotFoundException("Employee not found"));
    }

    // create employee
    @Transactional
    public EmployeeResponseDto createEmployee(EmployeeCreateDto createDto) {
        Employee employee = mapper.toEntity(createDto);
        employee = employeeRepo.save(employee);
        return mapper.toDto(employee);
    }

    // update employee
    @Transactional
    public EmployeeResponseDto updateEmployee(Long id, EmployeeUpdateDto updateDto) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new NotFoundException("Employee not found"));
        mapper.updateEntityFromDto(updateDto, employee);
        return mapper.toDto(employee);
    }

    // delete employee
    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new NotFoundException("Employee not found"));
        employeeRepo.delete(employee);
    }
}
