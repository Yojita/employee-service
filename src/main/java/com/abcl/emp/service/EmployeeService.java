package com.abcl.emp.service;

import java.util.List;

import com.abcl.emp.dto.EmployeeDTO;

public interface EmployeeService {

	public List<EmployeeDTO> getAllEmployees();

	public EmployeeDTO getEmpByEmpCode(String empCode);
}
