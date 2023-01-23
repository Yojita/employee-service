package com.abcl.emp.exception;

public class DuplicateEmployeeRecordException extends EmployeeServiceException {

	private static final long serialVersionUID = -3136467722648377751L;

	public DuplicateEmployeeRecordException() {
		super("Employee record with the employee code already exists");
	}
}
