package com.jmp.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

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
public class EdicaoContasController {

	@Autowired
	ContaRepository contaRepository;

	@GetMapping("/contas/admin/edicao-contas")
	public ModelAndView edicaoContas(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/edicao-contas");

		try {
			// capturando o usuário autenticado no sistema
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_auth");
			
			// resgatando os dados enviados pela URL (QueryString)
			Integer idConta = Integer.parseInt(request.getParameter("id"));

			// Verifico se o usuário está na sessão
			if (usuario == null) {
				// Se não estiver, redireciona para a página de login
				modelAndView.addObject("mensagem", "Sessão expirada. Faça login novamente.");
				modelAndView.addObject("mensagemTipo", "erro");
				return new ModelAndView("redirect:/contas"); // Ou qualquer outra página de login
			}

			// capturando a conta através do ID, do metodo que criei acima
			Conta conta = contaRepository.findById(idConta).get();
			
			if (conta != null && conta.getUsuario().getId() == usuario.getId()) {
				modelAndView.addObject("conta", conta);
			/*}else {
				modelAndView.setViewName("redirect:admin/edicao-contas");*/
			}

			// adiciona ao modelo para o Thymeleaf
			modelAndView.addObject("usuario_auth", usuario); 
		} catch (Exception e) {
			modelAndView.addObject("mensagem", e.getMessage());
			modelAndView.addObject("mensagemTipo", "erro"); // Tipo de mensagem
		}

		return modelAndView;
	}
		
	/* Método para capturar o SUBMIT POST do formulário da página de edição */
	@PostMapping("/contas/admin/edicao-contas-post")
	public ModelAndView edicaoContasPost(HttpServletRequest request) {
		//vai voltar para essa pagina	
		ModelAndView modelAndView = new ModelAndView("admin/edicao-contas");
			
		try {
			// capturando o usuário autenticado no sistema
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_auth");
				
			if (usuario == null) {
	            modelAndView.addObject("mensagem", "Usuario nao autenticado.");
	            modelAndView.addObject("mensagemTipo", "erro");
	            return modelAndView;
	        }
				       
			//para capturar os dados enviados pelo formulário crio uma conta
			Conta conta = new Conta();			
			
			// resgato todos os campos que a pagina manda pra mim
			
			conta.setId(Integer.parseInt(request.getParameter("idConta")));
			conta.setNome(request.getParameter("nome"));
			conta.setValor(Double.parseDouble(request.getParameter("valor")));
			conta.setData(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("data")));
			conta.setTipo(Integer.parseInt(request.getParameter("tipo")));
			conta.setDescricao(request.getParameter("descricao"));
			
			// peguei o usuario da sessao
			conta.setUsuario(usuario);
				
			//atualizando(gravando) no banco de dados
			contaRepository.save(conta);
				
			modelAndView.addObject("mensagem", "Conta atualizada com sucesso.");
		    modelAndView.addObject("mensagemTipo", "sucesso");  // Tipo de mensagem
		    
		    //a linha abaixo faz a pagina de edicao voltar preenchida depois que eu terminar a edicao e por isso passo o objeto conta de volta p pagina
			modelAndView.addObject("conta", conta);			
			modelAndView.addObject("usuario_auth", usuario);
			
			/*a pagina edicao esta programada para receber um objeto conta 
			 e separar o id, valor etc e e por isso que ela aceita a linha acima*/		
			System.out.println("FOI");

		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", e.getMessage());
		    modelAndView.addObject("mensagemTipo", "erro");  // Tipo de mensagem
		}
			
		return modelAndView;
		}
}
