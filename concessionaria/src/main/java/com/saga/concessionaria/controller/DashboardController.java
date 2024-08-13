package com.saga.concessionaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.saga.concessionaria.model.Consultor;
import com.saga.concessionaria.service.ConsultorService;

@Controller
public class DashboardController {

	@Autowired
	private ConsultorService consultorService;

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		// Obtendo o nome do usuário logado
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUsername = "";

		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String cpf = userDetails.getUsername(); // Supondo que o CPF é o nome de usuário

			// Buscar o consultor pelo CPF usando o serviço
			Consultor consultor = consultorService.findByCpf(cpf).orElse(null);
			if (consultor != null) {
				loggedInUsername = consultor.getNome(); // Obtém o nome do consultor
			}
		}

		// Adiciona o nome do usuário ao modelo
		model.addAttribute("username", loggedInUsername);
		return "dashboard"; // Retorna a view do dashboard
	}
}
