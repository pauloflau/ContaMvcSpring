package com.jmp.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jmp.entities.Conta;
import com.jmp.entities.Usuario;
import com.jmp.repositories.ContaRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ConsultaContasController {
	
	@Autowired
	ContaRepository contaRepository;
	
	@GetMapping("/contas/admin/consulta-contas")
	public ModelAndView consultaContas(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/consulta-contas");
		// RECUPERO o usuário da sessão que vem do AutenticarController para jogar na
		// pagina dashboard.jsp
		Usuario usuarioAuth = (Usuario) request.getSession().getAttribute("usuario_auth");

		// adiciona ao modelo para o Thymeleaf
		modelAndView.addObject("usuario_auth", usuarioAuth); // Passando para o template
		return modelAndView;
	}

	@PostMapping("/contas/admin/consulta-contas-post")
	public ModelAndView consultaContasPost(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("admin/consulta-contas");

		try {

			// capturando o usuário autenticado na sessão
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_auth");

			Date dataMin = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dataMin"));
			Date dataMax = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dataMax"));

			// fazendo a consulta no banco de dados
			List<Conta> contas =contaRepository.findContasByUsuarioIdAndDataRange(usuario.getId(), dataMin, dataMax);

			// enviando a lista para a página
			modelAndView.addObject("contas", contas);
			
		} catch (Exception e) {
			modelAndView.addObject("mensagem", e.getMessage());
		    modelAndView.addObject("mensagemTipo", "erro");  // Tipo de mensagem
		}

		return modelAndView;
	}
}
