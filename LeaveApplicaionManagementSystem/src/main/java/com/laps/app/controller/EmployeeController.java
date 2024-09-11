package com.laps.app.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.laps.app.exception.EmployeeNotFound;
import com.laps.app.model.Employee;
import com.laps.app.model.UserSession;
import com.laps.app.service.EmployeeService;
import com.laps.app.service.UserService;
import com.laps.app.validator.EmployeeValidator;

@Controller
@RequestMapping("/admin/employee")
@SessionAttributes(value = { "usession" }, types = { UserSession.class })
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeValidator employeeValidator;
	@Autowired
	private UserService userService;

	// same as model attribute name
	@InitBinder("employee")
	private void initEmployeeBinder(WebDataBinder binder) {
		binder.addValidators(employeeValidator);
	}

	// ------------------------
	// EMPLOYEE CRUD OPERATIONS
	// ------------------------

	@RequestMapping(value = "/list")
	public String employeeListPage(Model model) {
		List<Employee> employeeList = employeeService.findAllEmployees();
		model.addAttribute("employeeList", employeeList);

		return "employee-list";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String newEmployeePage(Model model) {
		model.addAttribute("employee", new Employee());
		//model.addAttribute("eidlist", employeeService.findAllEmployeeIDs());
		model.addAttribute("eidlist", employeeService.findAllEmployees());
		return "employee-new";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createNewEmployee(@ModelAttribute @Valid Employee employee, BindingResult result, Model model) {
		if (result.hasErrors()) {
			//model.addAttribute("eidlist", employeeService.findAllEmployeeIDs());
			model.addAttribute("eidlist", employeeService.findAllEmployees());
			return "employee-new";
		}

		employeeService.createEmployee(employee);

		String message = "New employee " + employee.getEmployeeId() + " was successfully created.";
		System.out.println(message);

		return "redirect:/admin/employee/list";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editEmployeePage(@PathVariable String id, Model model) {
		Employee employee = employeeService.findEmployee(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		model.addAttribute("eidlist", employeeService.findAllEmployeeIDs());

		return "employee-edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String editEmployee(@ModelAttribute @Valid Employee employee, BindingResult result, @PathVariable String id)
			throws EmployeeNotFound {
		if (result.hasErrors()) {
			return "employee-edit";
		}

		employeeService.changeEmployee(employee);

		String message = "Employee was successfully updated.";
		System.out.println(message);

		return "redirect:/admin/employee/list";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable String id, RedirectAttributes redirectAttrs) throws EmployeeNotFound {
		Employee employee = employeeService.findEmployee(Integer.parseInt(id));
		
		Integer count = employeeService.findIfUserExists(employee.getEmployeeId()).size();
		if(count > 0)
		{
			//return "employee-list";
			
			redirectAttrs.addFlashAttribute("errMessage", "DELETE FAILED");
			//return "/admin/employee/list";
			
			return "redirect:/admin/employee/list";
		}
		
		//userService.removeUserRoles(employee.getUser().getUserId());
		employeeService.removeEmployee(employee);
 
		String message = "The employee " + employee.getEmployeeId() + " was successfully deleted.";
		System.out.println(message);

		return "forward:/admin/employee/list";
	}
	@GetMapping("/export")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=employees_" + currentDateTime + ".csv";
		response.setHeader(headerKey, headerValue);

		List<Employee> listEmployees = employeeService.findAllEmployees();

		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		String[] csvHeader = { "Employee Id", "Manager Id", "Full Name", "Email", "Employee Type", "Leave Entitlement", "Medical Leave" };
		String[] nameMapping = { "employeeId", "managerId", "name", "email", "employeeType", "annualleaveentitlement", "medicalleave" };

		csvWriter.writeHeader(csvHeader);

		for (Employee employee : listEmployees) {
			csvWriter.write(employee, nameMapping);
		}

		csvWriter.close();
	}
	
}
