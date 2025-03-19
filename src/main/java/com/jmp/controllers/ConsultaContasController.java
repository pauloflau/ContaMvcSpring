package com.jmp.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
		
		// Verifica se o usuário está na sessão
		if (usuarioAuth == null) {
			// Se não estiver, redireciona para a página de login
			return new ModelAndView("redirect:/contas"); // Ou qualquer outra página de login
		}

		// adiciona ao modelo para o Thymeleaf
		modelAndView.addObject("usuario_auth", usuarioAuth); // Passando para o template
		
		return modelAndView;
	}

	@PostMapping("/contas/admin/consulta-contas-post")
	public ModelAndView consultaContasPost(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("admin/consulta-contas");

		try {

			// capturando o usuário autenticado na sessão
			Usuario usuarioAuth = (Usuario) request.getSession().getAttribute("usuario_auth");
			
			// Verifico se o usuário está na sessão
			if (usuarioAuth == null) {
				// Se não estiver, redireciona para a página de login
				modelAndView.addObject("mensagem", "Sessão expirada. Faça login novamente.");
				modelAndView.addObject("mensagemTipo", "erro");
				return new ModelAndView("redirect:/contas"); // Ou qualquer outra página de login
			}

			// Capturando as datas que o formulario enviou
			String dataMin = request.getParameter("dataMin");
			String dataMax = request.getParameter("dataMax");

			// pego as datas novamente para fazer meus formulario voltar preenchido com as
			// datas que estavam antes
			Date filtroDataMin = new SimpleDateFormat("yyyy-MM-dd").parse(dataMin);
			Date filtroDataMax = new SimpleDateFormat("yyyy-MM-dd").parse(dataMax);

			// fazendo uma nova consulta no banco de dados e faco com uma lista porque não
			// sei qtas contas tem
			List<Conta> contas = contaRepository.findContasByUsuarioIdAndDataRange(usuarioAuth.getId(), filtroDataMin, filtroDataMax);

			// enviando a lista para a página
			modelAndView.addObject("contas", contas);
			modelAndView.addObject("dataMin", dataMin);
			modelAndView.addObject("dataMax", dataMax);

			modelAndView.addObject("usuario_auth", usuarioAuth); // Passando para o template

		} catch (Exception e) {
			modelAndView.addObject("mensagem", e.getMessage());
			modelAndView.addObject("mensagemTipo", "erro"); // Tipo de mensagem
		}

		return modelAndView;
	}

	@GetMapping("/contas/admin/exclusao-contas") // e get porque os dados vem pela url
	public ModelAndView exclusaoContas(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("admin/consulta-contas");// vou voltar para essa pagina

		try {

			// resgatando os dados enviados pela URL (QueryString)
			Integer idConta = Integer.parseInt(request.getParameter("id"));
			String dataMin = request.getParameter("dataMin");
			String dataMax = request.getParameter("dataMax");

			// capturando o usuário autenticado no sistema
			Usuario usuarioAuth = (Usuario) request.getSession().getAttribute("usuario_auth");
			
			// Verifico se o usuário está na sessão
			if (usuarioAuth == null) {
				// Se não estiver, redireciona para a página de login
				modelAndView.addObject("mensagem", "Sessão expirada. Faça login novamente.");
				modelAndView.addObject("mensagemTipo", "erro");
				return new ModelAndView("redirect:/contas"); // Ou qualquer outra página de login
			}

			// capturando a conta através do ID, do metodo que criei acima
			Conta conta = contaRepository.findById(idConta).get();

			// verificando se a conta existe e se pertence ao usuário autenticado
			if (conta != null && conta.getUsuario().getId() == usuarioAuth.getId()) {
				// excluindo a conta
				contaRepository.deleteById(idConta);

				modelAndView.addObject("mensagem", "Conta excluida com sucesso.");
			    modelAndView.addObject("mensagemTipo", "sucesso");  // Tipo de mensagem				
			}
			
			// pego as datas novamente para fazer meus formulario voltar preenchido com as
			// datas que estavam antes
			Date filtroDataMin = new SimpleDateFormat("yyyy-MM-dd").parse(dataMin);
			Date filtroDataMax = new SimpleDateFormat("yyyy-MM-dd").parse(dataMax);

			// fazendo uma nova consulta no banco de dados e faco com uma lista porque não
			// sei qtas contas tem
			List<Conta> contas = contaRepository.findContasByUsuarioIdAndDataRange(usuarioAuth.getId(), filtroDataMin, filtroDataMax);

			// enviando a lista para a página
			modelAndView.addObject("contas", contas);
			modelAndView.addObject("dataMin", dataMin);
			modelAndView.addObject("dataMax", dataMax);
						
			modelAndView.addObject("usuario_auth", usuarioAuth); // Passando para o template

		} catch (Exception e) {			
			modelAndView.addObject("mensagem", e.getMessage());
		    modelAndView.addObject("mensagemTipo", "erro");  // Tipo de mensagem
		}

		return modelAndView;
	}
}
