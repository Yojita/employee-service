package com.abcl.emp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abcl.emp.dto.APIResponse;
import com.abcl.emp.dto.EmployeeDTO;
import com.abcl.emp.dto.EmployeeSignUpDTO;
import com.abcl.emp.exception.EmployeeServiceException;
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
	public ResponseEntity<Object> getEmpByEmpCode(@PathVariable("empCode") String empCode) {
		try {
			return ResponseEntity.ok(employeeService.getEmpByEmpCode(empCode));
		} catch (EmployeeServiceException e) {
			APIResponse response = new APIResponse();
			response.setErrorCode(HttpStatus.BAD_REQUEST.value());
			response.setErrorMessage(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping
	public ResponseEntity<Object> createEmployeeRecord(@Validated @RequestBody EmployeeSignUpDTO signUpDTO,
			Errors errors) {
		if (errors.hasErrors()) {
			APIResponse response = new APIResponse();
			response.setErrorCode(HttpStatus.BAD_REQUEST.value());
			response.setErrorMessage(errors.getAllErrors().stream().map(ObjectError::getDefaultMessage)
					.collect(Collectors.joining(",")));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		EmployeeDTO dto;
		try {
			dto = employeeService.createEmployeeRecord(signUpDTO);
		} catch (EmployeeServiceException e) {
			APIResponse response = new APIResponse();
			response.setErrorCode(HttpStatus.BAD_REQUEST.value());
			response.setErrorMessage(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
}
