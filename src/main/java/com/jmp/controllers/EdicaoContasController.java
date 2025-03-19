package com.jmp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jmp.entities.Conta;
import com.jmp.entities.Usuario;
import com.jmp.repositories.ContaRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class EdicaoContasController {

	@Autowired
	ContaRepository contaRepository;

	@GetMapping("/contas/admin/edicao-contas")
	public ModelAndView edicaoContas(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/edicao-contas");

		try {
			// capturando o usuário autenticado no sistema
			Usuario usuarioAuth = (Usuario) request.getSession().getAttribute("usuario_auth");
			
			// resgatando os dados enviados pela URL (QueryString)
			Integer idConta = Integer.parseInt(request.getParameter("id"));

			// Verifico se o usuário está na sessão
			if (usuarioAuth == null) {
				// Se não estiver, redireciona para a página de login
				modelAndView.addObject("mensagem", "Sessão expirada. Faça login novamente.");
				modelAndView.addObject("mensagemTipo", "erro");
				return new ModelAndView("redirect:/contas"); // Ou qualquer outra página de login
			}

			// capturando a conta através do ID, do metodo que criei acima
			Conta conta = contaRepository.findById(idConta).get();
			
			if (conta != null && conta.getUsuario().getId() == usuarioAuth.getId()) {
				modelAndView.addObject("conta", conta);
			/*}else {
				modelAndView.setViewName("redirect:admin/edicao-contas");*/
			}

			// adiciona ao modelo para o Thymeleaf
			modelAndView.addObject("usuario_auth", usuarioAuth); // Passando para o template
		} catch (Exception e) {
			modelAndView.addObject("mensagem", e.getMessage());
			modelAndView.addObject("mensagemTipo", "erro"); // Tipo de mensagem
		}

		return modelAndView;
	}
}
