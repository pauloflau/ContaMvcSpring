package com.jmp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jmp.entities.Usuario;
import com.jmp.helpers.Sha1CryptoHelper;
import com.jmp.repositories.UsuarioRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/contas")
public class AutenticarController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@GetMapping
	public ModelAndView autenticar() {
		ModelAndView modelAndView = new ModelAndView("autenticar");
		return modelAndView;
	}

	@PostMapping(value = "/autenticar-post")
	public ModelAndView autenticarPost(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("autenticar");
		try {
			String email = request.getParameter("email");
			String senha = Sha1CryptoHelper.getSha1Encrypt(request.getParameter("senha"));

			Usuario usuario = usuarioRepository.findByEmailAndSenha(email, senha);

			if (usuario != null) {
				modelAndView.setViewName("redirect:/contas/admin/dashboard");
			} else {
				throw new Exception("Acesso negado.  Usuario invalido.");
			}
		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		return modelAndView;

	}
}
