package com.abcl.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		return employeeService.getAllEmployees();
	}

	@GetMapping("/{empCode}")
	public EmployeeDTO getEmpByEmpCode(@PathVariable("empCode") String empCode) {
		return employeeService.getEmpByEmpCode(empCode);
	}
}
