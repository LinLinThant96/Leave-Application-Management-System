package com.laps.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laps.app.model.Employee;
import com.laps.app.model.LeaveDetails;
import com.laps.app.service.EmployeeService;
import com.laps.app.service.LeaveService;

@RestController
@RequestMapping("/api")
public class LapsApiController {
	
	
	

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {

		return employeeService.findAllEmployees();
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) {
		// Optional
		Employee employee = employeeService.findByEmployeeId(id);

		if (employee != null) {

			Employee emp = employee;

			return new ResponseEntity<Employee>(emp, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/employees")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee inEmployee) {
		try {
			Employee returnEmployee = employeeService.createEmployee(inEmployee);

			return new ResponseEntity<Employee>(returnEmployee, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}

	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> editEmployee(@PathVariable("id") int id, @RequestBody Employee inEmployee) {
		Employee employee = employeeService.findByEmployeeId(id);

		if (employee != null) {
			// employee is JPA managed object
			Employee emp = employee;

			emp.setName(inEmployee.getName());
			emp.setManagerId(inEmployee.getManagerId());
			emp.setEmail(inEmployee.getEmail());
			emp.setEmployeetype(inEmployee.getEmployeetype());
			emp.setAnnualleaveentitlement(inEmployee.getAnnualleaveentitlement());
			emp.setCompensationleave(inEmployee.getCompensationleave());
			emp.setMedicalleave(inEmployee.getMedicalleave());

			Employee updatedEmployee = employeeService.changeEmployee(emp);

			return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") int id) {
		try {
			Employee employee = employeeService.findByEmployeeId(id);
			employeeService.removeEmployee(employee);
			
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}
