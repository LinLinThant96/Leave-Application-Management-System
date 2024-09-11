package com.laps.app.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laps.app.model.CompensationDetails;
import com.laps.app.model.CompensationEventEnum;
import com.laps.app.model.CompensationLeave;
import com.laps.app.model.Employee;
import com.laps.app.service.CompensationDetailsService;
import com.laps.app.service.CompensationService;
import com.laps.app.service.EmployeeService;

@Controller
@RequestMapping("/manager/compensation")
public class ManagerCompensationController {

	@Autowired
	private EmployeeService empService;

	@Autowired
	private CompensationService CompensationService;

	@Autowired
	private CompensationDetailsService CompensationDetailsService;

	/** DISPLAYING OT HOURS CLAIM FOR MANAGER APPROVAL **/

	@GetMapping("/claim/display/{id}")
	public String newDepartmentPage(@PathVariable int id, Model model) {
		CompensationLeave cLeave = CompensationService.findcLeave(id);
		model.addAttribute("cLeave", cLeave);

		return "manager-compensation-approval";
	}

	@PostMapping("/claim/display/{id}")
	public String approveOrRejectCompensation(@RequestParam("decision") String decision, CompensationLeave cLeave,
			LocalDateTime cEndStartDate, Errors errors, Model model, @PathVariable Integer id) {
		System.out.println("calling");
		CompensationLeave cl = CompensationService.findcLeave(id);
		Employee em = empService.findEmployeeById(1);// findEmployeeById(cl.getEmployeeId()

		if (cLeave.getDecision().equals("YES")) {
			cl.setStatus(CompensationEventEnum.APPROVED);
			em.setCompensationleave(em.getCompensationleave() + cl.getCompensationHours() / 4);
		}
		if (cLeave.getDecision().equals("NO")) {
			cl.setStatus(CompensationEventEnum.REJECTED);
		}

		CompensationService.changecLeave(cl);
		empService.changeEmployee(em);
		String message = "Leave was successfully updated.";
		System.out.println(message);

//		  Employee myEmployee = Employee.findEmployeeById(100027)
//		  if (status=="YES") {
//			  
//			  myEmployee

//	    if (result.hasErrors())
//	      return "manager-compensation-detail";

		// if request param == yes,

//	    CompensationLeave c = cService.findcLeave(id);
//	    if (approve.getDecision().trim().equalsIgnoreCase(CourseEventEnum.APPROVED.toString())) {
//	      c.setStatus(CourseEventEnum.APPROVED);
//	    } else {
//	      c.setStatus(CourseEventEnum.REJECTED);
//	    }

//	    cService.changecLeave(c);
		// String message = "Leave was successfully updated.";
		// System.out.println(message);

		//return "ignore-overtime-input"; // this return path should be changed
		return "redirect:/manager/home"; // changed by akshaya
	}

	/** DISPLAYING COMPENSATION DETAILS CLAIM FOR MANAGER APPROVAL **/

	@GetMapping("/leave/display/details/{id}")
	public String newCompensationDetailsPage(@PathVariable int id, Model model) {
		CompensationDetails cDetails = CompensationDetailsService.findCDLeave(id);
		model.addAttribute("cDetails", cDetails);

		return "manager-compensation-details-approval";
	}

	@PostMapping("/leave/display/details/{id}")
	public String approveOrRejectCompensationDetails(@RequestParam("decision") String decision,
			CompensationDetails cDetails, LocalDateTime cEndStartDate, Errors errors, Model model,
			@PathVariable Integer id) {

		CompensationDetails cd = CompensationDetailsService.findCDLeave(id);
		Employee em = empService.findEmployeeById(4);// findEmployeeById(cl.getEmployeeId()

		if (cDetails.getDecision().equals("YES")) {
			cd.setStatus(CompensationEventEnum.APPROVED);
			em.setCompensationleave(em.getCompensationleave() - 1);
		}
		if (cDetails.getDecision().equals("NO")) {
			cd.setStatus(CompensationEventEnum.REJECTED);
		}

		CompensationDetailsService.changeCDLeave(cd);
		empService.changeEmployee(em);
		String message = "Leave was successfully updated.";
		System.out.println(message);

		//return "ignore-overtime-input"; // this return path should be changed
		return "redirect:/manager/home"; // changed by akshaya
	}
}
