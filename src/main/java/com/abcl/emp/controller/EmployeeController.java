package com.abcl.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abcl.emp.dto.EmployeeDTO;
import com.abcl.emp.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping
	public List<EmployeeDTO> getAllEmployees() {
		List<EmployeeDTO> employees = employeeService.getAllEmployees();
		return employees;
	}
}
