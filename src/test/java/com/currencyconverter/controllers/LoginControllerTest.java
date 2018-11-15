package com.currencyconverter.controllers;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author sandeepkumar
 *
 */

@WebMvcTest(value = LoginController.class)
@RunWith(SpringRunner.class)
public class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
	}

	@Test
	@WithMockUser(username = "user", password = "password", roles = { "ADMIN" })
	public void testLogin() throws Exception {

		ResultMatcher ok = MockMvcResultMatchers.status().isOk();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/testlogin").param("registration_message",
				"Registered successfully !!");

		this.mockMvc.perform(requestBuilder).andExpect(ok).andExpect(model().attributeExists("registration_message"))
				.andExpect(forwardedUrl("/views/login.jsp"));

	}

	@Test
	@WithMockUser(username = "user", password = "password", roles = { "ADMIN" })
	public void logoutPage() throws Exception {

		ResultMatcher ok = MockMvcResultMatchers.status().is3xxRedirection();

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/logout");
		this.mockMvc.perform(requestBuilder).andExpect(ok).andExpect(redirectedUrl("/login?logout=true"));

	}
}
