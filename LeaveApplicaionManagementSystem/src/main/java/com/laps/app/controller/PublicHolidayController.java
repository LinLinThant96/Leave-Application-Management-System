package com.laps.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.laps.app.exception.UserNotFound;
import com.laps.app.model.PublicHoliday;
import com.laps.app.model.User;
import com.laps.app.model.UserSession;
import com.laps.app.service.PublicHolidayService;

@Controller
@RequestMapping("/admin/holiday")
@SessionAttributes(value = { "usession" }, types = { UserSession.class })
public class PublicHolidayController {
	@Autowired
	private PublicHolidayService holidayService;

	@RequestMapping(value = "/list")
	public String publicHolidayList(Model model) {
		List<PublicHoliday> holidayList = holidayService.findAllHolidays();
		model.addAttribute("holidayList", holidayList);

		return "holiday-list";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String newHolidayPage(Model model) {
		model.addAttribute("holiday", new PublicHoliday());

		return "holiday-new";
	}

	@PostMapping("/create")
	public String newHolidayPage(@ModelAttribute("holiday") @Valid PublicHoliday publicHoliday, BindingResult result,
			Model model) {
		if (result.hasErrors()) {

			return "holiday-new";
		}

		holidayService.createPublicHoliday(publicHoliday);

		return "redirect:/admin/holiday/list";
	}

	@GetMapping("/edit/{id}")
	public String editHolidayPage(@PathVariable String id, Model model) {
		PublicHoliday holiday = holidayService.findPublicHoliday(Integer.parseInt(id));
		model.addAttribute("editholiday", holiday);

		return "holiday-edit";
	}

	@PostMapping("/edit/{id}")
	public String editUser(@ModelAttribute("editholiday") @Valid PublicHoliday publicHoliday, BindingResult result,
			@PathVariable String id) {
		if (result.hasErrors()) {
			return "holiday-edit";
		}
		holidayService.changePublicHoliday(publicHoliday);

		String message = "Holiday was successfully updated.";
		System.out.println(message);

		return "redirect:/admin/holiday/list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteHoliday(@PathVariable String id) {
		PublicHoliday holiday = holidayService.findPublicHoliday(Integer.parseInt(id));
		holidayService.removePublicHoliday(holiday);

		return "redirect:/admin/holiday/list";
	}

}
