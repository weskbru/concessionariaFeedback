package com.saga.concessionaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.saga.concessionaria.service.ConsultorService;

@Controller
public class ProfileController {
	
	@Autowired
	private ConsultorService consultorService;
	
	
	@GetMapping("/profile")
	public String profile(Model model) {
		
		
		return "profile";
	}
}
