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
public class ProfileController {

	@Autowired
	private ConsultorService consultorService;

	@GetMapping("/profile")
	public String profile(Model model) {

		// Obtendo o nome do usuário logado
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String cpf = userDetails.getUsername(); // suponha que o cpf é o nome do usuário

			// Busca o consultor pelo Cpf usuando o serviço
			Consultor consultor = consultorService.findByCpf(cpf).orElse(null);
			if (consultor != null) {
				model.addAttribute("username", consultor.getNome());
				model.addAttribute("email", consultor.getEmail());
				model.addAttribute("telefone", consultor.getTelefone());

			}
		}

		return "profile";
	}
}
