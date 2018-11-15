package com.currencyconverter.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import com.currencyconverter.model.User;
import com.currencyconverter.service.UserService;

@WebMvcTest(value = RegistrationController.class)
@RunWith(SpringRunner.class)
public class RegistrationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Test
	@WithMockUser(username = "user", password = "password", roles = { "ADMIN" })
	public void testRegister() throws Exception {

		ResultMatcher ok = MockMvcResultMatchers.status().isOk();
		ResultMatcher msg = MockMvcResultMatchers.model().attribute("registrationForm", any(User.class));

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/register");
		this.mockMvc.perform(builder).andExpect(ok).andExpect(msg).andExpect(forwardedUrl("/views/register.jsp"));
	}

}
