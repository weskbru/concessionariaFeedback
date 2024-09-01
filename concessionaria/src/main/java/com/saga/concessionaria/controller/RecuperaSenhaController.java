package com.saga.concessionaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RecuperaSenhaController {
	
	@GetMapping("/recuperaSenha")
	public String recuperaSenha() {
		return "recuperaSenha";
	}
}
