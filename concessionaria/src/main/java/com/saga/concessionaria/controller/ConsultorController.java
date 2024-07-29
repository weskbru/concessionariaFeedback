package com.saga.concessionaria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saga.concessionaria.model.Consultor;
import com.saga.concessionaria.service.ConsultorService;

@RestController
@RequestMapping("/api/consultores")
public class ConsultorController {
	
	private final ConsultorService consultorService;
	
	@Autowired
	public ConsultorController(ConsultorService consultorService) {
		this.consultorService = consultorService;
	}
	
	
	
	@PostMapping("/criar")
	public ResponseEntity<Consultor> criarConsultor(@ModelAttribute Consultor consultor){
		try {
			Consultor novoConsultor = consultorService.salvarConsultor(consultor);
			return new ResponseEntity<>(novoConsultor, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	
	
	
	
	
	
	
	
}
