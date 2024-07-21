
package com.saga.concessionaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.saga.concessionaria.infra.LoginRequest;
import com.saga.concessionaria.model.Consultor;
import com.saga.concessionaria.service.ConsultorService;

@Controller
public class LoginController {

	@Autowired
	private ConsultorService consultorService;

	@GetMapping("/login")
	public String home(Model model) {
		model.addAttribute("loginRequest", new LoginRequest());
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("loginRequest") LoginRequest loginResquest, Model model) {

		Consultor consultor = consultorService.findByCpfAndSenha(loginResquest.getCpf(), loginResquest.getSenha()).orElse(null);

		if (consultor != null) {
			model.addAttribute("nome", consultor.getNome());
			return "dashboard";
		} else {
			model.addAttribute("erro", "Cpf ou senha incorretos");
			return "cadastro";
		}

	}

}
