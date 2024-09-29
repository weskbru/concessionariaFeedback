package com.saga.concessionaria.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    
 // Método para processar a atualização do perfil
    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute Consultor consultorAtualizado, RedirectAttributes redirectAttributes) {
        try {
            // Obter o consultor logado
            Consultor consultorLogado = consultorService.obterConsultorLogado();
            
            if (consultorLogado == null) {
                redirectAttributes.addFlashAttribute("errorMessage", "Erro ao atualizar perfil. Consultor não encontrado.");
                return "redirect:/editProfile";
            }

            // Atualizar apenas os campos permitidos
            consultorLogado.setNome(consultorAtualizado.getNome());
            consultorLogado.setEmail(consultorAtualizado.getEmail());
            consultorLogado.setTelefone(consultorAtualizado.getTelefone());

            // Atualizar senha apenas se for preenchida
            if (consultorAtualizado.getSenha() != null && !consultorAtualizado.getSenha().isEmpty()) {
                consultorLogado.setSenha(consultorAtualizado.getSenha()); // Lembre-se de criptografar a senha aqui se necessário
            }

            // Atualizar o consultor no banco de dados
            consultorService.atualizarConsultor(consultorLogado);

            redirectAttributes.addFlashAttribute("successMessage", "Perfil atualizado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao atualizar o perfil: " + e.getMessage());
            return "redirect:/editProfile";
        }
        
        return "redirect:/dashboard"; // Redireciona após atualização
    }



}
