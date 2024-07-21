package com.saga.concessionaria.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.saga.concessionaria.model.Consultor;


@Service
public interface ConsultorService  {
	
	Consultor salvarConsultor(Consultor consultor);
	
	List<Consultor> listaConsultores();
	boolean cpfExiste(String cpf);
}