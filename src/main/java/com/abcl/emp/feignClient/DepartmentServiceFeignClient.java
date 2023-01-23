package com.abcl.emp.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.abcl.emp.dto.DepartmentServiceResponse;

@FeignClient(value = "api-gateway")
public interface DepartmentServiceFeignClient {

	@GetMapping("department-service/api/departments/{deptId}")
	public DepartmentServiceResponse getDeptId(@PathVariable Long deptId);
}
