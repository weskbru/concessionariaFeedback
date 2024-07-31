package com.saga.concessionaria.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	// Configura o Spring MVC para servir arquivos estáticos de diretórios
	// personalizados
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");

		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");

	}
}
