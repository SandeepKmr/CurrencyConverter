package com.currencyconverter.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 
 * @author sandeepkumar
 *
 */

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
	
	
	
	@GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){   
            new SecurityContextLogoutHandler().logout(request, response, auth);
            request.getSession().invalidate();
            
        }
        return "redirect:/";
    }
}
