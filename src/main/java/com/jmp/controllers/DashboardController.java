package com.jmp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jmp.entities.Usuario;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DashboardController {

	@GetMapping(value="contas/admin/dashboard")//apenas um endereco, podia ser qq coisa
	public ModelAndView dashboard(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/dashboard");
		
		 // RECUPERO o usuário da sessão que vem do AutenticarController para jogar na pagina dashboard.jsp
        Usuario usuarioAuth = (Usuario) request.getSession().getAttribute("usuario_auth");

        // adiciona ao modelo para o Thymeleaf
        modelAndView.addObject("usuario_auth", usuarioAuth); // Passando para o template        
		        
		return modelAndView;
	}
}
