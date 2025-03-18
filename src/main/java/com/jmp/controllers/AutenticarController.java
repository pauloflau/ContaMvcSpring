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

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/contas")
public class AutenticarController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@GetMapping
	public ModelAndView autenticar(HttpServletResponse response) {
	    // Adiciona os cabeçalhos para impedir que a página de login seja armazenada em cache
		//porem nao funciona para pagina anonima nos navegadores
	    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
	    response.setHeader("Pragma", "no-cache");
	    response.setHeader("Expires", "0");
	    
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
				
				//SALVO os dados do usuario em uma sessao E 
				request.getSession().setAttribute("usuario_auth", usuario);
								
				//REDIRECIONO para pagina de dashboards
				modelAndView.setViewName("redirect:/contas/admin/dashboard");
				
			} else {
				throw new Exception("Acesso negado.  Usuario invalido.");
			}
		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		return modelAndView;
	}
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response) {//pego um HttpServeletRequest para eu apagar os dados de sessao
		
		request.getSession().removeAttribute("usuario_auth");//apao o atributo usuario_auth		
		request.getSession().invalidate();//invalido a sessao		

        // Remover cookies de sessão, se necessário
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");        
        response.addCookie(cookie);
        
        // Limpar o cache para o logout
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
		
		//redieciono de volta para o raiz
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/contas");
		return modelAndView;
	}
}
