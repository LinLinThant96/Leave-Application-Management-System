package com.laps.app.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.laps.app.helper.DateHelper;
import com.laps.app.model.Employee;
import com.laps.app.model.LeaveDetails;
import com.laps.app.service.EmployeeService;


@Component
public class LeaveDetailsValidator implements Validator {
	@Autowired
	EmployeeService employeeservice;
	@Autowired
	DateHelper datehelper;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return LeaveDetails.class.isAssignableFrom(clazz);
	}
	@Override
	public void validate(Object target,Errors errors) {
		
		 System.out.println("called validator");
		 LeaveDetails leavedetails = (LeaveDetails) target; 
		 System.out.println("leavedetails "+leavedetails .getLeavestartdate() 
		 + "end" + leavedetails.getLeaveenddate() +"Employee Id "+leavedetails.getEmployeeId());
		 
		 Employee employee= employeeservice.findEmployeeById(leavedetails.getEmployeeId());
		 datehelper.loadHolidays();
		System.out.println("Employee Id in Validator"+employee.getEmployeeId() +"AL " +employee.getAnnualleaveentitlement() + " ML  "+ employee.getMedicalleave());
		 
		    if ((leavedetails .getLeavestartdate() != null && leavedetails .getLeaveenddate() != null) &&
		          (leavedetails .getLeavestartdate().compareTo(leavedetails .getLeaveenddate()) > 0)) 
		/*    		|| 	((leave.getLeaveStartDate() != null && leave.getLeaveEndDate() != null) 
		    				&& (leave.getLeaveStartDate().compareTo(leave.getLeaveEndDate()) == 0)))*/
		    {
		      errors.reject("leavestartdate", "Leave End date should be greater than or equal Leave Start Date.");
		      errors.rejectValue("leavestartdate", "error.dates", "leave enddate must be greater than leave startdate");  
		    }
		    
		    if ((leavedetails .getLeavestartdate() != null && leavedetails .getLeaveenddate() != null))
		  	{
		    int numberofdays =  datehelper.calculateLeavePeriod(leavedetails.getLeavestartdate(), leavedetails.getLeaveenddate());
	    	System.out.println("Line 50" +numberofdays +"And Leave Type" + leavedetails.getLeavetype());
		    if (numberofdays <= 14) 
			{
				numberofdays = datehelper.numberOfWorkingDaysBetween(leavedetails.getLeavestartdate(), leavedetails.getLeaveenddate());
				System.out.println("Line 54");
			}
		    //Validate Leavedays applied with leave balance for annualleave//
			if(("Annual Leave".equalsIgnoreCase(leavedetails.getLeavetype())) && employee.getAnnualleaveentitlement()< numberofdays)
					//|| (leavedetails.getLeavetype()=="Medical Leave") && employee.getMedicalleave()<numberofdays)
			{
				System.out.println("Line 60");
				errors.reject("leaveenddate", "Number of days applied is greater than available leave days");
			      errors.rejectValue("leavestartdate", "error.dates", "Number of leavedays applied should be within your leavebalance"); 
			}
			System.out.println("Line 64");
			 //Validate Leavedays applied with leave balance for annualleave//
			if((leavedetails.getLeavetype().equalsIgnoreCase("Medical Leave")) && employee.getMedicalleave()<numberofdays)
		{
			System.out.println("Line 68");
			errors.reject("leaveenddate", "Number of days applied is greater than available leave days");
		      errors.rejectValue("leavestartdate", "error.dates", "Number of leavedays applied should be within your leavebalance"); 
		}
			//Leavestart and end date cannot be a holiday//
			System.out.println("Line 73");
			if(datehelper.isHoliday(leavedetails.getLeavestartdate())) {
				errors.reject("leavestartdate", "Leave startdate cannot be a holiday");
			      errors.rejectValue("leavestartdate", "error.dates", "Leave start date cannot be a holiday"); 
			}
			System.out.println("Line 78");
			if(datehelper.isHoliday(leavedetails.getLeaveenddate())) {
				errors.reject("leaveenddate", "Leave enddate cannot be a holiday");
			      errors.rejectValue("leaveenddate", "error.dates", "Leave end date cannot be a holiday"); 
			}
		  	}
		    System.out.println("Line 84");
		   if((leavedetails.getLeavecategory() != null && leavedetails.getLeavecategory().equalsIgnoreCase("OverseasLeave") && (leavedetails.getContactnumber()== null))) {
			   errors.reject("contactnumber", "For overseas leave need a contact number");
			      errors.rejectValue("contactnumber", "error.contactnumber", "Please provide your overseas contact number"); 
		    
		   }
		    
		  
		    
	}
}
	


