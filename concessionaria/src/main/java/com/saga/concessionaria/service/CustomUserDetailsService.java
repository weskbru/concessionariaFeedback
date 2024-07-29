package com.saga.concessionaria.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.saga.concessionaria.model.Consultor;
import com.saga.concessionaria.repository.ConsultorRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ConsultorRepository consultorRepository;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        Optional<Consultor> consultor = consultorRepository.findByCpf(cpf);
        if (consultor.isEmpty()) {
            throw new UsernameNotFoundException("Consultor not found");
        }
        return new org.springframework.security.core.userdetails.User(
            consultor.get().getCpf(),
            consultor.get().getSenha(),
            new ArrayList<>()
        );
    }
}
