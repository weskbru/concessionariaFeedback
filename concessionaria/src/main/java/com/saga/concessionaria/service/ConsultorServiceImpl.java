package com.saga.concessionaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saga.concessionaria.model.Consultor;
import com.saga.concessionaria.repository.ConsultorRepository;


@Service
public class ConsultorServiceImpl implements ConsultorService {
	
	@Autowired
	private ConsultorRepository consultorRepository;
	
	

	@Override
	public Consultor salvarConsultor(Consultor consultor) {
		if(cpfExiste(consultor.getCpf())) {
			throw new IllegalArgumentException("CPF já cadastrado");
		}
		return consultorRepository.save(consultor);
	}

	@Override
	public List<Consultor> listaConsultores() {
		return consultorRepository.findAll();
	}
	
	@Override
	public boolean cpfExiste(String cpf) {
		return consultorRepository.findByCpf(cpf).isPresent();
	}

}
