package com.jmp.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//classe para apaar o historico e o cache das paginas do projeto, assim quando eu sair da pagina logada nao consigo
//clicar no botao voltar do navegador
public class CacheControlInterceptor implements HandlerInterceptor{
	
	@Override
	public void postHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		//limpar o cache das páginas após elas serem acessadas
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		
		// Limpar cache para páginas específicas, como a página de login ou logout
        if (request.getServletPath().equals("/logout")|| request.getServletPath().equals("/contas")) {
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
        }        
    	
	}	
}
