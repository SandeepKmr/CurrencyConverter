package com.currencyconverter.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.currencyconverter.model.User;
import com.currencyconverter.repository.UserRepository;
import com.currencyconverter.service.UserService;

/**
 * 
 * @author sandeepkumar
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Saves user to database after encoding the password.
	 */
	@Override
	public User saveUser(User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setConfirmPassword(passwordEncoder.encode(user.getConfirmPassword()));

		return userRepository.save(user);
	}

	@Override
	public boolean isUserExists(String emailId) {
		if (userRepository.findByEmailId(emailId) == null) {
			return false;
		}
		return true;
	}

}
