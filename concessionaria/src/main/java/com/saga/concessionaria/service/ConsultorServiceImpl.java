package com.saga.concessionaria.service;

import java.util.Optional;

import com.saga.concessionaria.model.Consultor;
import com.saga.concessionaria.repository.ConsultorRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
