package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.EmployeeDto;
import com.entity.Employee;
import com.service.EmployeeService;


@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService empService;
	
	@PostMapping("/addEmployee")
	public ResponseEntity addEmployee(@ModelAttribute EmployeeDto dto) {
		long employee = empService.addEmployee(dto);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Employee id : "+employee);
	}
	
	@GetMapping("/getAllEmp")
	public ResponseEntity getAll() {
		List<EmployeeDto> list = empService.getAllDetails();
		if (list!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Records not found");
		}
	}
	
	@DeleteMapping("/deleteEmp/{id}")
	public ResponseEntity deleteById(@PathVariable("id") long id) {
		long l = empService.deleteEmployeeById(id);
		if (l>0) {
			return ResponseEntity.status(HttpStatus.FOUND).body("Record deleted : "+id);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Records not found by given id");
		}
	}
	
	@PutMapping("/updateEmp/{id}")
	public ResponseEntity updateById(@PathVariable("id") long id,@ModelAttribute EmployeeDto dto) {
		Employee byId = empService.updateEmployeeById(id, dto);
		if (byId!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(byId);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Records not found");
		}
	}
	
	@GetMapping("/getEmpById/{id}")
	public ResponseEntity getRecordById(@PathVariable("id") long id) {
		EmployeeDto dto = empService.findEmployeeById(id);
		if (dto!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(dto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Records not found");
		}
	}
}
