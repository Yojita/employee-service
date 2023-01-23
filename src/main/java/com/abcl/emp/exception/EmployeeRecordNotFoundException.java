package com.abcl.emp.exception;

public class EmployeeRecordNotFoundException extends EmployeeServiceException {

	private static final long serialVersionUID = -6888785768091386269L;

	public EmployeeRecordNotFoundException() {
		super("Employee with given employee code does not exists");
	}

}
