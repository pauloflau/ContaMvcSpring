package com.jmp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jmp.entities.Usuario;
import com.jmp.repositories.UsuarioRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ContasMvc")
public class CriarUsuarioController {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping("/criar-usuario")
	public ModelAndView criarUsuario() {
		ModelAndView modelAndView = new ModelAndView("criar-usuario");
		return modelAndView; 
	}
	
	@PostMapping("/criar-usuario-post")
	public ModelAndView criarUsuarioPost(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("criar-usuario");
		
		try {
			
			Usuario usuario = new Usuario();
			
			usuario.setNome(request.getParameter("nome"));
			usuario.setEmail(request.getParameter("email"));
			usuario.setSenha(request.getParameter("senha"));
			
			usuarioRepository.save(usuario);
			
			modelAndView.addObject("mensagem", "Conta criada com sucesso");
		    modelAndView.addObject("mensagemTipo", "sucesso");  // Tipo de mensagem
			
		} catch (Exception e) {
			modelAndView.addObject("mensagem", "Erro: " + e.getMessage());
		    modelAndView.addObject("mensagemTipo", "erro");  // Tipo de mensagem
		}
		
		return modelAndView;
	}
	
}
