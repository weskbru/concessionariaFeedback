package com.saga.concessionaria.service;

import java.util.Optional;

import com.saga.concessionaria.model.Consultor;

public interface ConsultorService {
    Consultor salvarConsultor(Consultor consultor);
    
    boolean cpfExiste(String cpf);
    Optional<Consultor> findByCpf(String cpf);
    Optional<Consultor> findByCpfAndSenha(String cpf, String senha);

	Consultor obterConsultorLogado();

	
    
}
