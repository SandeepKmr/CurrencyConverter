package com.currencyconverter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.currencyconverter.model.CustomUserDetails;
import com.currencyconverter.model.Role;
import com.currencyconverter.model.User;
import com.currencyconverter.repository.UserRepository;
/**
 * 
 * @author sandeepkumar
 *
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private CustomUserDetails customUserDetails;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		
		
		User user = userRepository.findByEmailId(emailId);
		if (user == null) {
			throw new UsernameNotFoundException("No user present with emailId: " + emailId);
		} else {
			System.out.println(user.toString());
			List<SimpleGrantedAuthority> listOfAuthorities = new ArrayList<>();

			for (Role role : user.getRoles()) {
				listOfAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			}

			return new org.springframework.security.core.userdetails.User(emailId, user.getPassword(),
					listOfAuthorities);

		}
	}

}
