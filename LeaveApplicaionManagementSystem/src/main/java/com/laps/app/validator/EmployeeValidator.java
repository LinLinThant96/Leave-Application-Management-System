package com.laps.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.laps.app.model.Employee;

@Component
public class EmployeeValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Employee.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		//ValidationUtils.rejectIfEmpty(errors, "employeeId", "error.employee.employeeid.empty");
	   //ValidationUtils.rejectIfEmpty(errors, "name", "error.employee.name.empty");
	}

	

}
