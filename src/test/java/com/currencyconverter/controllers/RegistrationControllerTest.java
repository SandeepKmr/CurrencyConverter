package com.currencyconverter.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.hibernate.annotations.Any;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.currencyconverter.model.Role;
import com.currencyconverter.model.User;
import com.currencyconverter.service.UserService;

@WebMvcTest(value = RegistrationController.class)
@RunWith(SpringRunner.class)
public class RegistrationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private UserService userService;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
	}

	@Test
	@WithMockUser(username = "user", password = "password", roles = { "ADMIN" })
	public void testRegister() throws Exception {

		ResultMatcher ok = MockMvcResultMatchers.status().isOk();
		ResultMatcher msg = MockMvcResultMatchers.model().attribute("registrationForm", any(User.class));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/register");

		this.mockMvc.perform(requestBuilder).andExpect(ok).andExpect(msg)
				.andExpect(forwardedUrl("/views/register.jsp"));

	}

	@Test
	@WithMockUser(username = "user", password = "password", roles = { "ADMIN" })
	public void testRegisterUser() throws Exception {

		Role role1 = new Role(20, "ROLE_ADMIN");
		Role role2 = new Role(20, "ROLE_USER");
		Set<Role> roleList = new HashSet<Role>();
		roleList.add(role1);
		roleList.add(role2);

		User user1 = new User();
		user1.setUserId(30);
		user1.setEmailId("user1@gmail.com");
		user1.setPassword("password");
		user1.setConfirmPassword("Password@123");
		user1.setDateOfBirth(new Date());
		user1.setRoles(roleList);

		Mockito.when(userService.isUserExists(Mockito.anyString())).thenReturn(false);
		Mockito.when(userService.saveUser(Mockito.any(User.class))).thenReturn(user1);

		ResultMatcher ok = MockMvcResultMatchers.status().is3xxRedirection();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/register").flashAttr("registrationForm", user1)
				.with(csrf());

		this.mockMvc.perform(requestBuilder).andExpect(ok).andExpect(redirectedUrl("/login"));

	}
}
