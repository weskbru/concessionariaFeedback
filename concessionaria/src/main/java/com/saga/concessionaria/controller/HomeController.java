package com.saga.concessionaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.saga.concessionaria.model.Consultor;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		return "login";
	}
	
	@GetMapping("/cadastro")
	public String cadastro(Model model) {
		model.addAttribute("consultor" , new Consultor());
		return "cadastro";
	}
}
