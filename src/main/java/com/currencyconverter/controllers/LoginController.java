package com.currencyconverter.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@GetMapping("/login")
	public String login(@ModelAttribute("message") String val, Model model, String error, String logout) {
		if (error != null) {
			logger.warn("Invalid username or password");
			model.addAttribute("msg", "Username or password are invalid");
		}

		if (logout != null && !logout.isEmpty()) {
			logger.info("Logged out");
			model.addAttribute("msg", "Logged out successfully");
		}

		if (val != null && !val.isEmpty()) {
			logger.info(val);
			model.addAttribute("msg", val);
		}

		return "login";
	}
}
