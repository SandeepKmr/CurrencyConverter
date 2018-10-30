package com.currencyconverter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public String login(){
      /*  ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");*/
		System.out.println("Login Request !!");
        return "login";
    }
}
