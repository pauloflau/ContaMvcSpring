package com.jmp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ContasMvc")
public class AutenticarController {
	
	@GetMapping
	public ModelAndView autenticar() {
		ModelAndView modelAndView = new ModelAndView("autenticar");
		return modelAndView; 
	}
}
