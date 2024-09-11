package com.laps.app.service;

import java.util.ArrayList;
import java.util.List;

import com.laps.app.model.Employee;

public interface EmployeeService {
	
	List<Employee> findEmployeesByManagerId(Integer s);
	  Employee findEmployeeById(Integer s);
	  List<Employee> findAllEmployees();
	  Employee findEmployee(Integer empid);
	  Employee createEmployee(Employee emp);
	  Employee changeEmployee(Employee emp);
	  void removeEmployee(Employee emp);
	  List<String> findAllManagerNames();
	  List<Employee> findAllManagers();
	  List<Employee> findSubordinates(Integer employeeId);
	  List<String> findAllEmployeeIDs();
	  List<Employee> findIfUserExists(Integer employeeId);
		
		public Employee findByEmployeeId(Integer id);
		
		public void UpdateLeaveDays(Employee emp);
	  
}

