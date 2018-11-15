package com.currencyconverter.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.currencyconverter.service.UserService;

/**
 *
 * @author sandeepkumar
 *
 */

@Controller
public class LoginController {


	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@GetMapping({"/login","/testlogin"})
	public String login(@ModelAttribute("registration_message") String message, Model model, String error,
			String logout) {
		if (error != null) {
			logger.warn("Username or password are invalid");
			model.addAttribute("error_message", "Username or password are invalid.");
		}

		if (logout != null) {

			model.addAttribute("success_message", "Logged out successfully.");
			logger.info("Logged out successfully.");
		}

		if (!StringUtils.isBlank(message)) {
			logger.info(message);
			model.addAttribute("success_message", message);
		}
		return "login";
	}

	@GetMapping("/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
			request.getSession().invalidate();
			model.addAttribute("success_message", "Logged out successfully.");
			logger.info("User logged out successfully.");
		}
		return "redirect:/login?logout=true";
	}
}
