package com.abcl.emp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcl.emp.dto.EmployeeDTO;
import com.abcl.emp.entity.Employee;
import com.abcl.emp.repository.EmployeeRepository;
import com.abcl.emp.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		List<Employee> allEmployee = employeeRepository.findAll();
		List<EmployeeDTO> dtos = new ArrayList<>();
		for (Employee employee : allEmployee) {
			dtos.add(getDtoFromEntity(employee));
		}
		return dtos;
	}

	@Override
	public EmployeeDTO getEmpByEmpCode(String empCode) {
		Employee employee = employeeRepository.findByEmpCode(empCode);
		if (employee != null) {
			return getDtoFromEntity(employee);
		}
		return null;
	}

	private EmployeeDTO getDtoFromEntity(Employee employee) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setFirstName(employee.getFirstaName());
		dto.setLastName(employee.getLastName());
		dto.setEmpCode(employee.getEmpCode());
		dto.setBirthDate(employee.getBirthDate());
		return dto;
	}

}
