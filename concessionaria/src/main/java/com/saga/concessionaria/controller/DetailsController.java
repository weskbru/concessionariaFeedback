package com.saga.concessionaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DetailsController {
	@GetMapping("/details")
	public String recuperaSenha() {
		return "details";
	}
}
