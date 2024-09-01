package com.saga.concessionaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditProfileController {
	
	@GetMapping("/editProfile")
	public String editProfile() {
		return "editProfile";
	}
}
