package com.abcl.emp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcl.emp.feignClient.DepartmentServiceFeignClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class DepartmentService {
	@Autowired
	DepartmentServiceFeignClient feignClient;

	@CircuitBreaker(name = "departmentService", fallbackMethod = "fallbackGetDepartmentCode")
	public String getDepartmentCode(Long deptId) {
		return feignClient.getDeptId(deptId).getDeptCode();
	}

	public String fallbackGetDepartmentCode(Long deptId, Throwable t) {
		return "";
	}
}
