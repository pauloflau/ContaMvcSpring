package com.jmp.controllers;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

			// RECUPERO o usuário da sessão que vem do AutenticarController para jogar na pagina dashboard.jsp
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_auth");
			
			//capturar o mês e o ano atual
			Calendar calendar = Calendar.getInstance();
			Integer mes = calendar.get(Calendar.MONTH) + 1;
			Integer ano = calendar.get(Calendar.YEAR);
			
			//capturar o primeiro dia do mês
			Calendar primeiroDia = Calendar.getInstance();
			primeiroDia.set(ano, mes - 1, 1);
			
			//capturar o ultimo dia do mês
			Calendar ultimoDia = Calendar.getInstance();
			ultimoDia.set(ano, mes - 1, primeiroDia.getActualMaximum(Calendar.DAY_OF_MONTH));
			
			//o metodo find do contaRepository me gerara uma lista
			List<Conta> lista = contaRepository.findContasByUsuarioIdAndDataRange(usuario.getId(), primeiroDia.getTime(), ultimoDia.getTime());
			
			//calcular os dados que serão exibidos nbo dashboard
			Double totalContasReceber = 0.0;
			Double totalContasPagar = 0.0;
			Double saldo = 0.0;
			String situacao = "";
			
			//percorro alista que criei acima
			for(Conta conta : lista) {
				if(conta.getTipo() == 1) //conta a receber
					totalContasReceber += conta.getValor();
				else if(conta.getTipo() == 2) //conta a pagar
					totalContasPagar += conta.getValor();
			}
			
			saldo = totalContasReceber - totalContasPagar;
			if(saldo > 0)
				situacao = "Saldo positivo";
			else if(saldo < 0)
				situacao = "Saldo devedor";
			else
				situacao = "Saldo nulo";
			

			//enviando as variáveis para a página sempre especificando nome da variavel e depois o valor da variavel
			modelAndView.addObject("dataAtual", calendar.getTime());
			modelAndView.addObject("totalContasReceber",totalContasReceber);
			modelAndView.addObject("totalContasPagar", totalContasPagar);
			modelAndView.addObject("saldo", saldo);
			modelAndView.addObject("situacao", situacao);

			// adiciona ao modelo para o Thymeleaf
			modelAndView.addObject("usuario_auth", usuario); // Passando para o template
		} catch (Exception e) {
			modelAndView.addObject("mensagem", e.getMessage());
		    modelAndView.addObject("mensagemTipo", "erro");  // Tipo de mensagem
		}

		return modelAndView;
	}
}
