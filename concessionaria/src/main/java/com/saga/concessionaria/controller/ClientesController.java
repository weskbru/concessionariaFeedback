package com.saga.concessionaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientesController {
	@GetMapping("/clientes")
	public String listaClientes(Model model) {
		return "clientes";
	}
}
