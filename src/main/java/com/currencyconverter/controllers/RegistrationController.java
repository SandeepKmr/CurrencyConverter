package com.currencyconverter.controllers;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.currencyconverter.model.Role;
import com.currencyconverter.model.User;
import com.currencyconverter.service.UserService;

/**
 * 
 * @author sandeepkumar
 *
 */

@Controller
public class RegistrationController {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public ModelAndView register() {

		return new ModelAndView("register", "registrationForm", new User());

	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("registrationForm") User user, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "register";
		} else {
			Set<Role> role = new HashSet<>();
			role.add(new Role("ROLE_USER"));
			user.setRoles(role);
			userService.saveUser(user);
		}

		return "redirect:/login";

	}
}
