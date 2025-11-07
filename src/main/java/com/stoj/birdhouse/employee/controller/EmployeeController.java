package com.stoj.birdhouse.employee.controller;

import com.stoj.birdhouse.employee.dto.EmployeeCreateDto;
import com.stoj.birdhouse.employee.dto.EmployeeResponseDto;
import com.stoj.birdhouse.employee.dto.EmployeeUpdateDto;
import com.stoj.birdhouse.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // get all employees
    @GetMapping("/employees")
    public ResponseEntity<Page<EmployeeResponseDto>> getAllEmployees(@ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        Page<EmployeeResponseDto> employees = employeeService.getAllEmployees(pageable);
        return ResponseEntity.ok(employees);
    }

    // get employee by ids
    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployee(@PathVariable Long id) {
        EmployeeResponseDto employee = employeeService.getEmployee(id);
        return ResponseEntity.ok(employee);
    }

    // create employee
    @PostMapping("/employees")
    public ResponseEntity<EmployeeResponseDto> createEmployee(@Valid @RequestBody EmployeeCreateDto createDto) {
        EmployeeResponseDto employee = employeeService.createEmployee(createDto);
        return ResponseEntity.created(URI.create(String.format("/employees/%d", employee.getId()))).body(employee);
    }

    // update employee
    @PatchMapping("/employees/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeUpdateDto updateDto) {
        EmployeeResponseDto employee = employeeService.updateEmployee(id, updateDto);
        return ResponseEntity.ok(employee);
    }

    // delete employee
    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

}
