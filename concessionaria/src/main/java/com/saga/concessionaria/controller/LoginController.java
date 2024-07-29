package com.saga.concessionaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.saga.concessionaria.service.ConsultorService;

@Controller
public class LoginController {

	/*
	 * @Autowired private ConsultorService consultorService;
	 */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

	/*
	 * @PostMapping("/login") public String authenticate(@RequestParam("cpf") String
	 * cpf, @RequestParam("senha") String senha, Model model) { // Log de entrada
	 * System.out.println("Tentativa de login com CPF: " + cpf);
	 * 
	 * String erroLogin = consultorService.validateUser(cpf, senha); if (erroLogin
	 * != null) { model.addAttribute("erro", erroLogin); return "login"; } else { //
	 * Autenticação bem-sucedida, redirecionar para o dashboard return
	 * "redirect:/dashboard"; } }
	 */
}
