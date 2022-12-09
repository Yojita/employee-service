package com.abcl.emp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.abcl.emp.dto.EmployeeDTO;
import com.abcl.emp.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		return new ArrayList<>();
	}

}
