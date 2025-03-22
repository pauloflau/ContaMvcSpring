package com.jmp.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class DashboardController {

	@Autowired
	ContaRepository contaRepository;

	@GetMapping(value = "contas/admin/dashboard") // apenas um endereco, podia ser qq coisa
	public ModelAndView dashboard(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/dashboard");
		try {
			// capturando o usuário autenticado na sessão
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_auth");
			
			// Verifico se o usuário está na sessão
			usuarioConectado(modelAndView, usuario);

			// Capturando as datas que o formulario enviou, mas como aqui e a primeira vez, tenho que guardar a data no atributo mesAno
			String mesAno = request.getParameter("mesAno");
			if (mesAno==null || mesAno.isEmpty()) {
				// Usando LocalDate para obter a data atual
			    LocalDate dataAtual = LocalDate.now();			    
			    // Formatando a data no formato "yyyy-MM"
			    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
			    //salvo na variavel mes ano
		        mesAno = dataAtual.format(formatter);
			}
				
			String ano = mesAno.substring(0, 4);
			String mes = mesAno.substring(5, 7);
			
			 // Converte o mês e ano para Integer
	        Integer mesInt = Integer.parseInt(mes);
	        Integer anoInt = Integer.parseInt(ano);

			// fazendo uma nova consulta no banco de dados e faco com uma lista porque não sei qtas contas tem
			List<Conta> lista = contaRepository.findContasByUsuarioIdAndMesAno(usuario.getId(), mesInt, anoInt);
			
			// calcular os dados que serão exibidos no dashboard
			Double totalContasReceber = 0.0;
			Double totalContasPagar = 0.0;
			Double saldo = 0.0;
			String situacao = "";

			// percorro alista que criei acima
			for (Conta conta : lista) {
				if (conta.getTipo() == 1) // conta a receber
					totalContasReceber += conta.getValor();
				else if (conta.getTipo() == 2) // conta a pagar
						totalContasPagar += conta.getValor();
			}

			saldo = totalContasReceber - totalContasPagar;
			if (saldo > 0)
				situacao = "Saldo positivo";
			else if (saldo < 0)
				situacao = "Saldo devedor";
			else
				situacao = "Saldo nulo";

			// enviando as variáveis para a página sempre especificando nome da variavel e depois o valor da variavel
			modelAndView.addObject("totalContasReceber", totalContasReceber);
			modelAndView.addObject("totalContasPagar", totalContasPagar);
			modelAndView.addObject("saldo", saldo);
			modelAndView.addObject("situacao", situacao);

			modelAndView.addObject("mesAno", mesAno);

			modelAndView.addObject("usuario_auth", usuario); // Passando para o template

			

		} catch (Exception e) {
			modelAndView.addObject("mensagem", e.getMessage());
			modelAndView.addObject("mensagemTipo", "erro"); // Tipo de mensagem
		}

		return modelAndView;
	}
	
	@PostMapping("/contas/admin/dashboard-contas-post")
	public ModelAndView dashboardPost(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("admin/dashboard");

		try {
			// capturando o usuário autenticado na sessão
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_auth");
			
			// Verifico se o usuário está na sessão
			usuarioConectado(modelAndView, usuario);

			// Capturando as datas que o formulario enviou
			String mesAno = request.getParameter("mesAno");
			String ano = mesAno.substring(0, 4);
			String mes = mesAno.substring(5, 7);
			
			 // Converte o mês e ano para Integer
	        Integer mesInt = Integer.parseInt(mes);
	        Integer anoInt = Integer.parseInt(ano);

			// fazendo uma nova consulta no banco de dados e faco com uma lista porque não sei qtas contas tem
			List<Conta> lista = contaRepository.findContasByUsuarioIdAndMesAno(usuario.getId(), mesInt, anoInt);
			
			// calcular os dados que serão exibidos no dashboard
			Double totalContasReceber = 0.0;
			Double totalContasPagar = 0.0;
			Double saldo = 0.0;
			String situacao = "";

			// percorro alista que criei acima
			for (Conta conta : lista) {
				if (conta.getTipo() == 1) // conta a receber
					totalContasReceber += conta.getValor();
				else if (conta.getTipo() == 2) // conta a pagar
						totalContasPagar += conta.getValor();
			}

			saldo = totalContasReceber - totalContasPagar;
			if (saldo > 0)
				situacao = "Saldo positivo";
			else if (saldo < 0)
				situacao = "Saldo devedor";
			else
				situacao = "Saldo nulo";

			// enviando as variáveis para a página sempre especificando nome da variavel e depois o valor da variavel
			modelAndView.addObject("totalContasReceber", totalContasReceber);
			modelAndView.addObject("totalContasPagar", totalContasPagar);
			modelAndView.addObject("saldo", saldo);
			modelAndView.addObject("situacao", situacao);

			modelAndView.addObject("mesAno", mesAno);

			modelAndView.addObject("usuario_auth", usuario); // Passando para o template
						
						

		} catch (Exception e) {
			modelAndView.addObject("mensagem", e.getMessage());
			modelAndView.addObject("mensagemTipo", "erro"); // Tipo de mensagem
		}

		return modelAndView;
	}
	
	private ModelAndView usuarioConectado(ModelAndView modelAndView, Usuario usuario) {							
		// Verifico se o usuário está na sessão
		if (usuario == null) {// Se não estiver, redireciona para a página de login				
			modelAndView.addObject("mensagem", "Sessão expirada. Faça login novamente.");
			modelAndView.addObject("mensagemTipo", "erro");
			return new ModelAndView("redirect:/contas"); 
		}
		return modelAndView;
	}
}
