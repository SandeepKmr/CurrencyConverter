package com.currencyconverter.service.impl;

import com.currencyconverter.model.Role;
import com.currencyconverter.model.User;
import com.currencyconverter.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author sandeepkumar
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomUserDetailsServiceTest {

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@SuppressWarnings("unused")
	@Test
	public void testLoadUserByUsername() throws UsernameNotFoundException {

		Role role1 = new Role(10, "ROLE_ADMIN");
		Role role2 = new Role(10, "ROLE_USER");
		Set<Role> roleList = new HashSet<Role>();
		roleList.add(role1);
		roleList.add(role2);

		User user = new User();
		user.setUserId(100);
		user.setEmailId("user@gmail.com");
		user.setPassword("password");
		user.setConfirmPassword("password");
		user.setDateOfBirth(new Date());
		user.setRoles(roleList);

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setConfirmPassword(passwordEncoder.encode(user.getConfirmPassword()));

		List<SimpleGrantedAuthority> userListOfAuthorities = new ArrayList<>();
		userListOfAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		userListOfAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		org.springframework.security.core.userdetails.User detailUser = new org.springframework.security.core.userdetails.User(
				user.getEmailId(), user.getPassword(), userListOfAuthorities);

		Mockito.when(userRepository.findByEmailId(Mockito.anyString())).thenReturn(user);
		assertThat(userRepository.findByEmailId(Mockito.anyString())).isEqualTo(user);

		if (user == null) {

			throw new UsernameNotFoundException("No user present with this emailId");
		} else {
			List<SimpleGrantedAuthority> listOfAuthorities = new ArrayList<>();

			for (Role role : user.getRoles()) {
				listOfAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			}

			assertThat(new org.springframework.security.core.userdetails.User("user@gmail.com", user.getPassword(),
					listOfAuthorities)).isEqualTo(detailUser);

		}
	}

}
