package com.abcl.emp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.abcl.emp.dto.EmployeeDTO;
import com.abcl.emp.dto.EmployeeSignUpDTO;
import com.abcl.emp.entity.Employee;
import com.abcl.emp.exception.DuplicateEmployeeRecordException;
import com.abcl.emp.exception.EmployeeRecordNotFoundException;
import com.abcl.emp.exception.EmployeeServiceException;
import com.abcl.emp.repository.EmployeeRepository;
import com.abcl.emp.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Value("${departmentService.base-url}")
	private String departmentServiceURL;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	DepartmentService departmentService;

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		List<Employee> allEmployee = employeeRepository.findAll();
		List<EmployeeDTO> dtos = new ArrayList<>();
		for (Employee employee : allEmployee) {
			dtos.add(convertEntityToDto(employee));
		}
		return dtos;
	}

	@Override
	public EmployeeDTO getEmpByEmpCode(String empCode) throws EmployeeServiceException {
		Employee employee = employeeRepository.findByEmpCode(empCode);
		if (employee != null) {
			return convertEntityToDto(employee);
		} else {
			throw new EmployeeRecordNotFoundException();
		}
	}

	@Override
	public EmployeeDTO createEmployeeRecord(EmployeeSignUpDTO dto) throws EmployeeServiceException {
		Employee newEmp = convertSignUpDtoToEntity(dto);
		newEmp = employeeRepository.save(newEmp);
		return convertEntityToDto(newEmp);
	}

	private EmployeeDTO convertEntityToDto(Employee employee) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setFirstName(employee.getFirstaName());
		dto.setLastName(employee.getLastName());
		dto.setEmpCode(employee.getEmpCode());
		dto.setBirthDate(employee.getBirthDate());
		dto.setDeptCode(departmentService.getDepartmentCode(employee.getDeptId()));
		return dto;
	}

	private Employee convertSignUpDtoToEntity(EmployeeSignUpDTO dto) throws EmployeeServiceException {
		Employee employee = new Employee();
		employee.setFirstaName(dto.getFirstName());
		employee.setLastName(dto.getLastName());
		employee.setBirthDate(dto.getBirthDate());
		employee.setDeptId(dto.getDeptId());

		StringBuilder builder = new StringBuilder();
		builder.append(dto.getFirstName().toUpperCase().charAt(0));
		builder.append(dto.getLastName().toUpperCase().charAt(0));
		builder.append(dto.getBirthDate().getYear());

		Employee existingEmployee = employeeRepository.findByEmpCode(builder.toString());
		if (existingEmployee != null) {
			throw new DuplicateEmployeeRecordException();
		}

		employee.setEmpCode(builder.toString());
		employee.setDeptId(dto.getDeptId());
		return employee;

	}

	public WebClient getWebClient() {
		return WebClient.create(departmentServiceURL);
	}

}
