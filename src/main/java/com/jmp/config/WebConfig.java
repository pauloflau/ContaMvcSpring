package com.jmp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jmp.interceptors.AdminAccessInterceptor;
import com.jmp.interceptors.CacheControlInterceptor;

@Configuration  // define a classe como uma configuração do Spring.
public class WebConfig implements WebMvcConfigurer{//implemento essa interface para personalizar o comportamente da configuracao spring mvc
	@Override
    public void addInterceptors(InterceptorRegistry registry) {//metodo para registrar o interceptor
        registry.addInterceptor(new AdminAccessInterceptor())//registro o interceptor e associo as URL(tudo que começa com /admin/).
                .addPathPatterns("/contas/admin/**")  // Especifica as URLs que o interceptor deve capturar
                .excludePathPatterns("/contas/admin/login", "/contas/admin/register",
                		"/admin/register");  // Exclui URLs específicas
        
        registry.addInterceptor(new CacheControlInterceptor()).addPathPatterns("/**");
    }
}
