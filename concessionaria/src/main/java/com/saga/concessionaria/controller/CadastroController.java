package com.saga.concessionaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.saga.concessionaria.model.Consultor;
import com.saga.concessionaria.service.ConsultorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class CadastroController {
	
	@Autowired
	private ConsultorService consultorService;
	

	@GetMapping("/cadastro")
	public String cadastro(Model model) {
		model.addAttribute("consultor" , new Consultor());
		return "cadastro";
	}
	
	@PostMapping("/cadastro")
	public String cadastroConsultor(@ModelAttribute("consultor") Consultor consultor, Model model) {
		try {
			consultorService.salvarConsultor(consultor);
			return "redirect:/login";
		} catch (IllegalArgumentException e) {
			// Log de erro
	        System.err.println("Erro ao salvar consultor: " + e.getMessage());
			model.addAttribute("erro", e.getMessage());
			return "cadastro";
		}
		
		
	}
	
}
