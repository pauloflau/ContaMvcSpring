package com.jmp.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminAccessInterceptor implements HandlerInterceptor {

	@Override  //so preciso desse metodo que implemento da interface
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler)
			throws Exception {

		//verificar se o usuário está navegando em alguma rota da pasta /admin
		if(request.getServletPath().toLowerCase().contains("/contas/admin/")) {
			//verificando se o usuário não está autenticado (gravado em sessão)
			if(request.getSession().getAttribute("usuario_auth") == null) {
				//redirecionando para a página inicial do sistema
				response.sendRedirect("/contas");
				return false;
			}
		}		
		return true;
	}
}
