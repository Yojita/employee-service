package com.abcl.emp.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class EmployeeSignUpDTO {

	@NotEmpty(message = "{app.validation.emp.firstname}")
	private String firstName;
	@NotEmpty(message = "{app.validation.emp.lastname}")
	private String lastName;
	@Past(message = "{app.validation.emp.birthdate}")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate birthDate;
	private Long deptId;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	@Override
	public String toString() {
		return "EmployeeSighUpDTO [firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", deptId=" + deptId + "]";
	}

}
