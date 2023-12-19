package com.service;

import java.util.List;

import com.dto.EmployeeDto;
import com.entity.Employee;

public interface EmployeeService {
	long addEmployee(EmployeeDto dto);
	List<EmployeeDto> getAllDetails();
	long deleteEmployeeById(long id);
	Employee updateEmployeeById(long id,EmployeeDto dto);
	EmployeeDto findEmployeeById(long id);
}
