package com.laps.app.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.CompiledExpression;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.laps.app.exception.UserNotFound;
import com.laps.app.model.Employee;
import com.laps.app.model.Role;
import com.laps.app.model.User;
import com.laps.app.model.UserSession;
import com.laps.app.service.EmployeeService;
import com.laps.app.service.RoleService;
import com.laps.app.service.UserService;
import com.laps.app.validator.UserValidator;

@Controller
@RequestMapping(value = "/admin/user")
@SessionAttributes(value = { "usession" }, types = { UserSession.class })
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private UserValidator userValidator;

	@InitBinder("user")
	private void initUserBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}

	@RequestMapping(value = "/list")
	public String userListPage(Model model) {
		List<User> userList = userService.findAllUsers();
		model.addAttribute("userList", userList);

		return "user-list";
	}

	// @RequestMapping(value = "/create", method = RequestMethod.GET)
	@GetMapping("/create")
	public String newUserPage(Model model) {
		Employee emp = new Employee();
		model.addAttribute("user", new User(emp));
		// List<String> eidList = employeeService.findAllEmployeeIDs();
		//List<Employee> eidList = employeeService.findAllEmployees();
		List<String> userEmpList = userService.findAllUserEmpIDs();
		List<Employee> empList = employeeService.findAllEmployees();
		List<Employee> eidList = empList.stream()
				.filter(e -> !userEmpList.contains(e.getEmployeeId().toString()))
				.collect(Collectors.toList());
		
		
		
		
		model.addAttribute("eidlist", eidList);
		List<Role> roles = roleService.findAllRoles();
		model.addAttribute("roles", roles);

		return "user-new";
	}

	@PostMapping("/create")
	public String createNewUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
		if (result.hasFieldErrors("name") || result.hasFieldErrors("roleSet") || result.hasFieldErrors("password")) {
			// List<String> eidList = employeeService.findAllEmployeeIDs();

			//List<Employee> eidList = employeeService.findAllEmployees();
			List<String> userEmpList = userService.findAllUserEmpIDs();
			List<Employee> empList = employeeService.findAllEmployees();
			List<Employee> eidList = empList.stream()
					.filter(e -> !userEmpList.contains(e.getEmployeeId().toString()))
					.collect(Collectors.toList());
			
			model.addAttribute("eidlist", eidList);
			List<Role> roles = roleService.findAllRoles();
			model.addAttribute("roles", roles);

			return "user-new";
		}

		// The roles from user input only has id, we retrieve
		// the respective Role object
		List<Role> newRoleSet = new ArrayList<Role>();
		if(user.getRoleSet() != null)
		{
			
			user.getRoleSet().forEach(role -> {
				Role completeRole = roleService.findRole(role.getRoleId());
				newRoleSet.add(completeRole);
			});
			user.setRoleSet(newRoleSet);
		}
		else {
				Role completeRole = roleService.findRole("staff");
				newRoleSet.add(completeRole);
			user.setRoleSet(newRoleSet);
		}
		
		

		userService.createUser(user);

		return "redirect:/admin/user/list";
	}

	@GetMapping("/edit/{id}")
	public String editUserPage(@PathVariable String id, Model model) {
		User user = userService.findUser(Integer.parseInt(id));
		model.addAttribute("user", user);

		List<Role> roles = roleService.findAllRoles();
		model.addAttribute("allRoles", roles);

		return "user-edit";
	}

	@PostMapping("/edit/{id}")
	public String editUser(@ModelAttribute @Valid User user, BindingResult result, @PathVariable String id)
			throws UserNotFound {
		if (result.hasErrors()) {
			return "user-edit";
		}

		userService.changeUser(user);

		String message = "User was successfully updated.";
		System.out.println(message);

		return "redirect:/admin/user/list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteUser(@PathVariable String id, RedirectAttributes redirectAttrs) throws UserNotFound {
		User user = userService.findUser(Integer.parseInt(id));
		
		if(user.getRoleSet().size() > 0)
		{
			redirectAttrs.addFlashAttribute("errMessage", "DELETE FAILED");
			redirectAttrs.addFlashAttribute("userid", id);
			return "redirect:/admin/user/list";
		}
		
		
		userService.removeUser(user);

		String message = "The user " + user.getUserId() + " was successfully deleted.";
		System.out.println(message);

		return "redirect:/admin/user/list";
	}
	
	@GetMapping("/export")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
		response.setHeader(headerKey, headerValue);

		List<User> listUsers = userService.findAllUsers();

		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		String[] csvHeader = { "User Id", "User Name" };
		String[] nameMapping = { "userId", "name" };

		csvWriter.writeHeader(csvHeader);

		for (User user : listUsers) {
			csvWriter.write(user, nameMapping);
		}

		csvWriter.close();
	}

}
