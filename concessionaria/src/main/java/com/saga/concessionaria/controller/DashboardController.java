package com.saga.concessionaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class DashboardController {
	
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		return "dashboard"; // nome do template que sera direcionado 
	}
	
}
