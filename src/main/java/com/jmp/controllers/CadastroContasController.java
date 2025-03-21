package com.jmp.controllers;

import java.text.SimpleDateFormat;

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
public class CadastroContasController {
	
	@Autowired
	ContaRepository contaRepository;
	
	@GetMapping("/contas/admin/cadastro-contas")
	public ModelAndView cadastroContas(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/cadastro-contas");

		// RECUPERO o usuário da sessão que vem do AutenticarController para jogar na
		// pagina dashboard.jsp
		Usuario usuarioAuth = (Usuario) request.getSession().getAttribute("usuario_auth");

		// adiciona ao modelo para o Thymeleaf
		modelAndView.addObject("usuario_auth", usuarioAuth); // Passando para o template

		return modelAndView;
	}

	@PostMapping("/contas/admin/cadastro-contas-post")
	//receberei os dados que o formulario envia pelo HttpServletRequest pois ele resgata tudo que o formulario manda
	public ModelAndView cadastroContasPost(HttpServletRequest request) {
		// depois ele vai voltar para a pagina cadastro-contas
		ModelAndView modelAndView = new ModelAndView("admin/cadastro-contas");

		try {
			// capturar o usuário autenticado na sessão
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_auth");
			
			if (usuario == null) {
	            modelAndView.addObject("mensagem", "Usuario nao autenticado.");
	            modelAndView.addObject("mensagemTipo", "erro");
	            return modelAndView;
	        }			
			
			Conta conta = new Conta();
			// resgato todos os campos que a pagina manda pra mim
			conta.setNome(request.getParameter("nome"));
			conta.setData(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("data")));
			conta.setValor(Double.parseDouble(request.getParameter("valor")));
			conta.setTipo(Integer.parseInt(request.getParameter("tipo")));
			conta.setDescricao(request.getParameter("descricao"));
			// peguei o usuario da sessao
			conta.setUsuario(usuario);

			// gravar a conta no banco de dados			
			contaRepository.save(conta);
			
			modelAndView.addObject("mensagem", "Conta cadastrada com sucesso.");
		    modelAndView.addObject("mensagemTipo", "sucesso");  // Tipo de mensagem
		} catch (Exception e) {
			modelAndView.addObject("mensagem", e.getMessage());
		    modelAndView.addObject("mensagemTipo", "erro");  // Tipo de mensagem
		}
		return modelAndView;
	}
}
