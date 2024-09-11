package com.laps.app.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laps.app.model.CompensationDetails;
import com.laps.app.model.CompensationEventEnum;
import com.laps.app.model.CompensationLeave;
import com.laps.app.model.Employee;
import com.laps.app.model.UserSession;
import com.laps.app.service.CompensationDetailsService;
import com.laps.app.service.CompensationService;
import com.laps.app.service.EmployeeService;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


@Controller
@RequestMapping(value = "/staff")
public class CompensationController {
  @Autowired
  private CompensationService compensationService;
  
  @Autowired
  private EmployeeService eService;
  
  @Autowired
  private CompensationDetailsService compensationDetailsService;

//  @Autowired
//  private CourseValidator courseValidator;
//
//  @InitBinder("course")
//  private void initCourseBinder(WebDataBinder binder) {
//    binder.addValidators(courseValidator);
//  }

  /**
   * COURSE CRUD OPERATIONS
   * 
   * @return
   */
  @RequestMapping(value = "compensation/history")
  public String employeeCourseHistory(HttpSession session, Model model) {
    UserSession usession = (UserSession) session.getAttribute("usession");
    
    //System.out.println(usession.getEmployee());
    
    List<CompensationLeave> employeecLeave = compensationService.findcLeaveByEID(1);
    		//usession.getEmployee().getEmployeeId());
    model.addAttribute("chistory", employeecLeave);
      
    return "compensation-my-history";
  }

  
  /** TO APPLY FOR OT HOURS **/
  
  @GetMapping("/compensation/create")
  public String newCoursePage(Model model) {
    model.addAttribute("cLeave", new CompensationLeave());
    
    return "compensation-new";
  }

  @PostMapping("/compensation/create")
  public String createNewCourse(
		  @RequestParam("cLeaveStartDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime cLeaveStartDate,
		  @RequestParam("cLeaveEndDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime cEndStartDate, 
		  @Valid CompensationLeave cLeave, 
		  Errors errors, 
		  Model model) {
//    if (result.hasErrors()) {
//      return "compensation-new";
//    }
    
    //UserSession usession = (UserSession) session.getAttribute("usession");
	  cLeave.setcLeaveStartDate(cLeaveStartDate);
	  cLeave.setcLeaveEndDate(cEndStartDate);
	  cLeave.setCompensationHours(ChronoUnit.HOURS.between(cLeaveStartDate, cEndStartDate));;
    cLeave.setEmployeeId(1);
    		//usession.getEmployee().getEmployeeId());
    cLeave.setStatus(CompensationEventEnum.SUBMITTED);
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    //cLeave.setcLeaveEndDate= (formatter);
    //cLeave.setcLeaveStartDate(LocalDateTime.now());
    //cLeave.setcLeaveEndDate(LocalDateTime.now());
    compensationService.createcLeave(cLeave);
    
    String message = "New compensation application " + cLeave.getCompensationLeaveId() + " was successfully created.";
    System.out.println(message);
    
    return "redirect:/staff/compensation/history";
    
  }
  
  /** TO APPLY FOR OT LEAVE **/
  
  @GetMapping("/compensation/apply")
  public String newApplyPage(Model model) {

    model.addAttribute("cDetail", new CompensationDetails());
    
    Employee emp = eService.findEmployeeById(4);
	//usession.getEmployee().getEmployeeId());
    model.addAttribute("emp", emp);
    
    return "compensation-apply";
  }
  
  @PostMapping("/compensation/apply")
  public String createNewOtLeave(
		  @RequestParam("otLeaveStartDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate otLeaveStartDate,
		  @RequestParam("timeOfDay") String timeOfDay, 
		  @Valid CompensationDetails cDetails, 
		  Errors errors, 
		  Model model) {
	  // set pm/am start & end time
	  
	  
	  int amHourStart = 8;
	  int amHourEnd = 12;
	  int pmHourStart = 13;
	  int pmHourEnd = 17;
	  
	  /** code if else for AM PM**/

	  
	  if (timeOfDay.equals("AM")) {
		  LocalDateTime startdate = otLeaveStartDate.atTime(amHourStart,0);
		  LocalDateTime enddate = otLeaveStartDate.atTime(amHourEnd,0);
		  cDetails.setOtLeaveStartDate(startdate);
		  cDetails.setOtLeaveEndDate(enddate);
	  }
	  
	  if (timeOfDay.equals("PM")) {
		  LocalDateTime startdate = otLeaveStartDate.atTime(pmHourStart,0);
		  LocalDateTime enddate = otLeaveStartDate.atTime(pmHourEnd,0);
		  cDetails.setOtLeaveStartDate(startdate);
		  cDetails.setOtLeaveEndDate(enddate);
	  }
	
	    cDetails.setEmployeeId(4);
	    cDetails.setLeaveDuration(ChronoUnit.HOURS.between(cDetails.getOtLeaveStartDate(), cDetails.getOtLeaveEndDate()));;
		//usession.getEmployee().getEmployeeId());
cDetails.setStatus(CompensationEventEnum.SUBMITTED);
 
//	  LocalDateTime ldt = otLeaveStartDate.atStartOfDay();

	  
	  compensationDetailsService.createCDLeave(cDetails);
    String message = "New compensation details application " + cDetails.getCompensationDetailsFormId() + " was successfully created.";
    System.out.println(message);
    return "redirect:/staff/compensation/history";
    
  }
  

  /** EXTRA FUNCTIONALITIES NOT CODED VIEW YET **/
  /* @GetMapping("/compensation/edit/{id}")
  public String editCompensationPage(@PathVariable Integer id, Model model) {
    CompensationLeave cLeave = compensationService.findcLeave(id);
    model.addAttribute("cLeave", cLeave);
    
    return "compensation-edit";
  }

  @PostMapping("/compensation/edit/{id}")
  public String editCourse(@ModelAttribute @Valid CompensationLeave cLeave, BindingResult result, @PathVariable Integer id,
      HttpSession session) { //throws CourseNotFound {
    if (result.hasErrors())
      return "compensation-edit";
    
    //System.out.println("DATES****" + cLeave.getcLeaveStartDate() + cLeave.getcLeaveEndDate());
    
    UserSession usession = (UserSession) session.getAttribute("usession");
    cLeave.setEmployeeId(usession.getEmployee().getEmployeeId());
    cLeave.setStatus(CompensationEventEnum.UPDATED);
    
    compensationService.changecLeave(cLeave);
    
    return "redirect:/staff/compensation/history";
  }
 */ 
  @GetMapping("/compensation/claim")
  public String newCompensationPage(Model model) {
    model.addAttribute("cLeave", new CompensationLeave());
    
    return "compensation-claim";
  }

  @RequestMapping(value = "/compensation/withdraw/{id}")
  public String deleteCourse(@PathVariable Integer id) { //throws CourseNotFound {
    CompensationLeave cLeave = compensationService.findcLeave(id);
    
    cLeave.setStatus(CompensationEventEnum.WITHDRAWN);
    compensationService.changecLeave(cLeave);

    String message = "Course " + cLeave.getCompensationLeaveId() + " was successfully withdrawn.";
    System.out.println(message);
    
    return "redirect:/staff/compensation/history";
  }

}