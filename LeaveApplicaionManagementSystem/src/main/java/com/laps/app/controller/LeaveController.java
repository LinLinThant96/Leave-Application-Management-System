package com.laps.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laps.app.helper.DateHelper;
import com.laps.app.model.Employee;
import com.laps.app.model.LeaveDetails;
import com.laps.app.model.LeaveEventEnum;
import com.laps.app.model.UserSession;
import com.laps.app.service.EmailService;
import com.laps.app.service.EmployeeService;
import com.laps.app.service.LeaveService;
import com.laps.app.validator.LeaveDetailsValidator;


//import com.laps.app.service.LeaveService;
@Controller
@RequestMapping("/staff/leave")
public class LeaveController {
	
 @Autowired
private LeaveService leaveService;
@Autowired
private DateHelper datehelper;

@Autowired
private EmployeeService employeeService;

 @Autowired
private LeaveDetailsValidator leaveDetailsValidator;
 
 @Autowired
 private EmailService emailService;

@InitBinder("leavedetails")
 private void initLeaveBinder(WebDataBinder binder) {
   binder.addValidators(leaveDetailsValidator);
 }
 
@RequestMapping(value = "/history")
	public String employeeLeaveHistory(HttpSession session, Model model) {
		
		System.out.println("Calling history");
		UserSession usersession = (UserSession)session.getAttribute("usession");
		//System.out.println(usession.getEmployee());
		Employee employee = employeeService.findEmployee(usersession.getEmployee().getEmployeeId());
		List<LeaveDetails> employeeLeaves =leaveService.findLeavesByEID(usersession.getEmployee().getEmployeeId());
		//LeaveDetails ld=employeeLeaves.get(0);
		//System.out.println("Printing Leave Id "+ld.getLeaveformid() +"Size "+employeeLeaves.size());
		model.addAttribute("shistory", employeeLeaves);
		model.addAttribute("employee", employee);
		System.out.println("Returning to Leave History");
		return "leave-my-history";
}



@GetMapping("/history/{pageNo}")
public String findPaginatedLeaveHistory(@PathVariable(value = "pageNo") int pageNo, Model model, HttpSession session) {
	System.out.println("Calling NEW history");
	int pageSize = 3;
	UserSession usersession = (UserSession)session.getAttribute("usession");
	Employee employee = employeeService.findEmployee(usersession.getEmployee().getEmployeeId());
	List<LeaveDetails> employeeLeaves =leaveService.findLeavesByEID(usersession.getEmployee().getEmployeeId());

	model.addAttribute("shistory", employeeLeaves);
	model.addAttribute("employee", employee);
	
	Page<LeaveDetails> page = leaveService.findPaginated(usersession.getEmployee().getEmployeeId(),pageNo, pageSize);
	System.out.println("Paged List Size: " +page.getSize());
	System.out.println("Paged List getTotalPages: " +page.getTotalPages());
	System.out.println("Paged List getTotalElements: " +page.getTotalElements());
	
	List<LeaveDetails> listLeaveHistory = page.getContent();
	if(!listLeaveHistory.isEmpty()) {
		LeaveDetails ld=listLeaveHistory.get(0);
		System.out.println("Paged List Size:  "+ld.getLeaveformid() +" Size "+listLeaveHistory.size());
		System.out.println("Paged Date:  "+ld.getLeavereason() );
	}
	model.addAttribute("listLeaveHistory", listLeaveHistory);
	model.addAttribute("currentPage",pageNo);
	model.addAttribute("totalPages", page.getTotalPages());
	model.addAttribute("totalItems", page.getTotalElements());
	
	return "leave-my-history";
}


@GetMapping("/applyleave")
	public String showLeaveForm(HttpSession session, Model model) {
		UserSession usession = (UserSession)session.getAttribute("usession");
		LeaveDetails leavedetails=new LeaveDetails();
		leavedetails.setEmployeeId(usession.getEmployee().getEmployeeId());
		model.addAttribute("leavedetails",leavedetails);
		return "leaveform";
	}

	@PostMapping("/save")
	public String saveLeaveForm(@Valid  @ModelAttribute("leavedetails") LeaveDetails leavedetails,BindingResult bindingresult,Model model,HttpSession session) {
		//if errors,return to applyleaveform
		System.out.println("Save Leave Controller");
		 UserSession usersession = (UserSession)session.getAttribute("usession");
		// Integer loginEmpId = usession.getEmployee().getEmployeeId();
		if(bindingresult.hasErrors())
		{ 
			System.out.println("binding has error");
			return "leaveform";
		}

	   // private int calculateLeavePeriod(final LocalDate fromDate, final LocalDate endDate)
		int numberofdays =  datehelper.calculateLeavePeriod(leavedetails.getLeavestartdate(), leavedetails.getLeaveenddate());
		if (numberofdays <= 14) 
		{
			int numberofeligibledays = datehelper.numberOfWorkingDaysBetween(leavedetails.getLeavestartdate(), leavedetails.getLeaveenddate());
			leavedetails.setLeaveduration(numberofeligibledays);
		}
		else{
			leavedetails.setLeaveduration(numberofdays);
			 }
		
		leavedetails.setLeavestatus(LeaveEventEnum.APPLIED);
		leaveService.processLeaveRequest(leavedetails);
			
		Employee emp= employeeService.findByEmployeeId(usersession.getEmployee().getManagerId());
		String body = "Leave application submission for approval";
		String from = usersession.getEmployee().getEmail();
		String to = emp.getEmail();
		String subject = "Leave Application from,  "+usersession.getEmployee().getName();
		emailService.sendEmail(from, to, subject, body);
	
		session.setAttribute("usession", usersession);
		
		return "redirect:/staff/leave/history/1";
		//return "viewpersonalhistory";
	}
 
	@GetMapping("/updateleave/{id}")
	//id here is leave table id auto generated
	public String showEditLeaveForm(@PathVariable("id")Integer id, Model model,HttpSession session) {
		UserSession usersession = (UserSession)session.getAttribute("usession");
		LeaveDetails leavedetails = leaveService.findByLeaveId(id);
		System.out.println("Employee Id in update leave id:"+leavedetails.getEmployeeId());
		model.addAttribute("leavedetails",leavedetails);
		session.setAttribute("usession", usersession);
		return "updateleaveform";
	}
	
	
	@PostMapping("/saveupdateleave")
	public String saveUpdateLeaveForm(@Valid  @ModelAttribute("leavedetails") LeaveDetails leavedetails,BindingResult bindingresult,Model model, HttpSession session) {
		//if errors,return to applyleaveform
		UserSession usersession = (UserSession)session.getAttribute("usession");
		System.out.println("Update Leave Controller"+leavedetails.getEmployeeId());
		if(bindingresult.hasErrors())
		{ 
			System.out.println("binding has error");
			return "leaveform";
		}
		int numberofdays = datehelper.numberOfWorkingDaysBetween(leavedetails.getLeavestartdate(), leavedetails.getLeaveenddate());
		leavedetails.setLeaveduration(numberofdays);
		leavedetails.setLeavestatus(LeaveEventEnum.UPDATED);
		leaveService.processLeaveRequest(leavedetails);
		session.setAttribute("usession", usersession);
		return "redirect:/staff/leave/history/1";
		//return "viewpersonalhistory";
	}
	
	 @GetMapping("/deleteleave/{id}")
	//your leave is deleted.If leave approved cannot be deleted.	
	 public String deleteLeave (Model model,@PathVariable("id")Integer id, HttpSession session){
		 UserSession usersession = (UserSession)session.getAttribute("usession");
		 LeaveDetails leavedetails=leaveService.findByLeaveId(id);
 	  // leave record is made invalid in db and saved but not removed completely
		 //if(leavedetails.getLeavestatus().APPROVED.equals("APPROVED"))
		 if(leavedetails.getLeavestatus().toString().equalsIgnoreCase("APPROVED"))
			 leavedetails.setLeavestatus(LeaveEventEnum.CANCELLED);
		 else
			 leavedetails.setLeavestatus(LeaveEventEnum.DELETED);
		 
		leaveService.processLeaveRequest(leavedetails);
		session.setAttribute("usession", usersession);
		return "redirect:/staff/leave/history/1";
		//return "viewpersonalhistory";
	}
	 
	 @GetMapping("/cancelleave/{id}")	
	 public String canceleLeave (Model model,@PathVariable("id")Integer id, HttpSession session){
		UserSession usersession = (UserSession)session.getAttribute("usession");
		LeaveDetails leavedetails=leaveService.findByLeaveId(id);
 	  	leavedetails.setLeavestatus(LeaveEventEnum.CANCELLED);
		leaveService.processLeaveRequest(leavedetails);
		session.setAttribute("usession", usersession);
		return "redirect:/staff/leave/history/1";
		//return "viewpersonalhistory";
	}
	 
}

