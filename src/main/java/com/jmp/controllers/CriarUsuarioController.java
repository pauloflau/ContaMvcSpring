package com.jmp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CriarUsuarioController {

	@GetMapping("/criar-usuario")
	public ModelAndView criarUsuario() {
		ModelAndView modelAndView = new ModelAndView("criar-usuario");
		return modelAndView;
	}
}
