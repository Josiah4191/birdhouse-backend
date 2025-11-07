package com.stoj.birdhouse.employee.repository;

import com.stoj.birdhouse.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {}
