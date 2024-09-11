package com.laps.app;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.laps.app.model.Employee;
import com.laps.app.model.LeaveDetails;
import com.laps.app.model.LeaveEventEnum;
import com.laps.app.model.Role;
import com.laps.app.model.User;
import com.laps.app.repo.EmployeeRepository;
import com.laps.app.repo.LeaveRepository;
import com.laps.app.repo.RoleRepository;
import com.laps.app.repo.UserRepository;

@SpringBootApplication
public class CaLapsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaLapsApplication.class, args);
	}
/*
	@Bean
	CommandLineRunner loadData(RoleRepository roleRepository, 
    EmployeeRepository employeeRepository,
    UserRepository userRepository,
   LeaveRepository leaveRepository) {
    return (args) -> {
    	// Add a few Roles
		      Role systemadminRole = roleRepository.save(new Role(01, "Administrator", "System administrator"));
		      Role staffRole = roleRepository.save(new Role(02, "Staff", "Staff members"));
		      Role managerRole = roleRepository.save(new Role(03, "Manager", "Manager"));
		      
		      // Add a few Employees
		      employeeRepository.save(new Employee(310017, "isssdmin",200027,"issadmin@email.com","office",14,60));
		      employeeRepository.save(new Employee(200027, "Esther Tan",null,"issmanager1@email.com","professional",18,60));
		      employeeRepository.save(new Employee(212025, "Nguyen Tri Tin",null,"issmanager2@email.com","professional",18,60));
		      employeeRepository.save(new Employee(101034, "sharmine", 200027,"staffsharmine@email.com","professional",18,60));
		      employeeRepository.save(new Employee(112025, "gao", 212025,"staffakshaya@email.com","professional",18,60));
		      employeeRepository.save(new Employee(100027, "akshaya", 212025,"staffgao@email.com","professional",18,60));
		      
		      
		      
		      // Add a few Users
		      User issadmin = new User(310017,"issadmin", "password",1);
		      User esther = new User(200027,"esther", "password",2);
		      User tin = new User(212025,"tin", "password",3);
		      User sharmine = new User(101034,"sharmine", "password",4);
		      User akshaya = new User(100027,"akshaya", "password",5);
		      User gao = new User(112025,"gao", "password",7);
		      User systemadmin=new User(010017,"systemadmin","password",7);

		      
//		      
//		      // Add a few Users
//		      User systemadmin=new User("systemadmin","password",2);
//		      User esther = new User("esther", "password",3);
//		      User tin = new User("tin", "password",4);
//		      User sharmine = new User("sharmine", "password",5);
//		      User akshaya = new User("akshaya", "password",6);
//		      User gao = new User("gao", "password",7);
//		      User issadmin = new User("issadmin", "password",1);
//		      
		      //SetRoles
		      //system admin is the one who maintains the entire system//
		      //issadmin is office staff with 14 days leave//
		      systemadmin.setRoleSet(Arrays.asList(systemadminRole));
		      userRepository.saveAndFlush(systemadmin);
		      
		      issadmin.setRoleSet(Arrays.asList(staffRole));
		      userRepository.saveAndFlush(issadmin);
		      
		      esther.setRoleSet(Arrays.asList(staffRole, managerRole));
		      userRepository.saveAndFlush(esther);
		      
		      tin.setRoleSet(Arrays.asList(staffRole,managerRole));
		      userRepository.saveAndFlush(tin);
		      
		      sharmine.setRoleSet(Arrays.asList(staffRole));
		      userRepository.saveAndFlush(sharmine);
		      
		      akshaya.setRoleSet(Arrays.asList(staffRole));
		      userRepository.saveAndFlush(akshaya);
		      
		      gao.setRoleSet(Arrays.asList(staffRole));
		      userRepository.saveAndFlush(gao);
		      
		      //Add a few Leave data
		      //Leaveform1
		     LeaveDetails leave1= new LeaveDetails();
		     leave1.setEmployeeId(1);
		     leave1.setLeavecategory("local");
		     leave1.setLeaveduration(2);
		     leave1.setLeavestartdate(LocalDate.now().plusDays(2));
		     leave1.setLeaveenddate(LocalDate.now().plusDays(4));
		     leave1.setLeavereason( "casual leave");
		     leave1.setLeaveStatus(LeaveEventEnum.APPLIED);
		     leave1.setLeavetype("Annual Leave");
		     leave1.setWorkdissemination("Pending work at progress before leave day");
		     leaveRepository.save(leave1);
		  
		   //Leaveform2
		     LeaveDetails leave2= new LeaveDetails();
		     leave2.setEmployeeId(2);
		     leave2.setLeavecategory("overseas");
		     leave2.setLeaveduration(3);
		     leave2.setLeavestartdate(LocalDate.now().plusDays(3));
		     leave2.setLeaveenddate(LocalDate.now().plusDays(6));
		     leave2.setLeavereason( "personal");
		     leave2.setLeaveStatus(LeaveEventEnum.APPLIED);
		     leave2.setLeavetype("Annual Leave");
		     leave2.setWorkdissemination("work disssipated to sharmine and Akshaya");
		     //since the leave is overseas contact number is added
		     leave2.setContactnumber(65328475);
		     leaveRepository.save(leave2);
	   
		      
    };
	
}*/
}


