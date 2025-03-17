package com.jmp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {

	@GetMapping(value="contas/admin/dashboard")
	public ModelAndView dashboard() {
		ModelAndView modelAndView = new ModelAndView("admin/dashboard");
		return modelAndView;
	}
}
