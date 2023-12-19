package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.EmployeeDto;
import com.entity.Employee;
import com.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository empRepository;
	
	@Override
	public long addEmployee(EmployeeDto dto) {
		Employee employee = empRepository.save(Employee.builder().empName(dto.getEmpName()).email(dto.getEmail())
				.phoneNumber(dto.getPhoneNumber()).managerId(dto.getManagerId()).build());
		return employee.getId();
	}

	@Override
	public List<EmployeeDto> getAllDetails() {
		List<Employee> list = empRepository.findAll();
		if (list!=null) {
			List<EmployeeDto> list2 = list.stream().map(t-> EmployeeDto.builder().empName(t.getEmpName()).email(t.getEmail()).phoneNumber(t.getPhoneNumber())
					.managerId(t.getManagerId()).build()).collect(Collectors.toList());
			return list2;
		}else {
			return null;
		}
	}

	@Override
	public long deleteEmployeeById(long id) {
		try {
			empRepository.deleteById(id);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}

	@Override
	public Employee updateEmployeeById(long id,EmployeeDto dto) {
		try {
			Employee employee = empRepository.findById(id).get();
			if (employee!=null) {
				employee.setEmail(dto.getEmail());
				employee.setEmpName(dto.getEmpName());
				employee.setPhoneNumber(dto.getPhoneNumber());
				employee.setManagerId(dto.getManagerId());
				return empRepository.save(employee);
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public EmployeeDto findEmployeeById(long id) {
		try {
			Employee employee = empRepository.findById(id).get();
			if (employee!=null) {
				EmployeeDto dto = EmployeeDto.builder().email(employee.getEmail()).empName(employee.getEmpName()).phoneNumber(employee.getPhoneNumber())
				.managerId(employee.getManagerId()).build();
				return dto;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

}
