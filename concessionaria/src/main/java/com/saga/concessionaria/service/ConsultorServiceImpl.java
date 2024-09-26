package com.saga.concessionaria.service;

import java.util.Optional;

import com.saga.concessionaria.model.Consultor;
import com.saga.concessionaria.repository.ConsultorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ConsultorServiceImpl implements ConsultorService {

    @Autowired
    private ConsultorRepository consultorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Consultor salvarConsultor(Consultor consultor) {
    	// Verifica se o CPF já existe antes de salvar
    	System.out.println("Verificando se o CPF já existe: " + consultor.getCpf());
        if (cpfExiste(consultor.getCpf())) {
        	System.out.println("CPF já cadastrado: " + consultor.getCpf()); // Log para depuração
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        // Criptografa a senha antes de salvar
        consultor.setSenha(passwordEncoder.encode(consultor.getSenha()));
        return consultorRepository.save(consultor);
    }

    @Override
    public boolean cpfExiste(String cpf) {
        return consultorRepository.findByCpf(cpf).isPresent();
    }

    @Override
    public Optional<Consultor> findByCpf(String cpf) {
        return consultorRepository.findByCpf(cpf);
    }

    @Override
    public Optional<Consultor> findByCpfAndSenha(String cpf, String senha) {
        Optional<Consultor> consultor = consultorRepository.findByCpf(cpf);
        if (consultor.isPresent() && passwordEncoder.matches(senha, consultor.get().getSenha())) {
            return consultor;
        }
        return Optional.empty();
    }
    
    @Override
    public Consultor obterConsultorLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String cpf = authentication.getName(); // O CPF do consultor logado
            Optional<Consultor> consultorOpt = consultorRepository.findByCpf(cpf);
            return consultorOpt.orElse(null); // Retorna o consultor ou null se não encontrado
        }
        return null; // Retorna null se não estiver autenticado
    }
    
}
