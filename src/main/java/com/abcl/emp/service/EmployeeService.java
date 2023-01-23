package com.abcl.emp.service;

import java.util.List;

import com.abcl.emp.dto.EmployeeDTO;
import com.abcl.emp.dto.EmployeeSignUpDTO;
import com.abcl.emp.exception.EmployeeServiceException;

public interface EmployeeService {

	public List<EmployeeDTO> getAllEmployees();

	public EmployeeDTO getEmpByEmpCode(String empCode) throws EmployeeServiceException;

	public EmployeeDTO createEmployeeRecord(EmployeeSignUpDTO dto) throws EmployeeServiceException;
}
