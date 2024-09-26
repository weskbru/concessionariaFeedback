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
public class EditProfileController {

    @Autowired
    private ConsultorService consultorService;

    @GetMapping("/editProfile")
    public String editProfile(Model model) {
        // Obtendo o consultor logado atraves do service
    	Consultor consultor = consultorService.obterConsultorLogado();
    	
    	if(consultor != null) {
    		// Adiciona ps dados do consultor ao modelo para pre-enchimento 
    		model.addAttribute("username", consultor.getNome());
            model.addAttribute("email", consultor.getEmail());
            model.addAttribute("telefone", consultor.getTelefone());
    	} else {
            // Opcional: Redirecionar para uma página de erro ou login se não estiver autenticado
            return "redirect:/login"; // Supondo que você tenha uma página de login
        } 
    	
    	// Retorna a página editProfile.html
        return "editProfile"; 
    }
}
