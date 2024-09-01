package com.saga.concessionaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.dao.DataIntegrityViolationException;
import com.saga.concessionaria.model.Consultor;
import com.saga.concessionaria.service.ConsultorService;

@Controller
public class CadastroController {

    @Autowired
    private ConsultorService consultorService;

    @GetMapping("/cadastro")
    public String mostrarFormularioDeCadastro(Model model) {
        model.addAttribute("consultor", new Consultor());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String criarConsultor(@ModelAttribute("consultor") Consultor consultor, Model model) {
    	model.addAttribute("erro", null); // Resetar mensagem de erro antes de começar a validação
        try {
            consultorService.salvarConsultor(consultor);
            return "redirect:/login"; // Redireciona para a página de login após o cadastro bem-sucedido
            
        } catch (IllegalArgumentException e) {
        	
        	// Adiciona a mensagem de erro ao modelo e exibe na página de cadastro
            model.addAttribute("erro", e.getMessage());
            return "cadastro"; // Volta para o formulário de cadastro com a mensagem de erro
        }
    }
}
