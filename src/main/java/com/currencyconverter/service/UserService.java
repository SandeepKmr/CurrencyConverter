package com.currencyconverter.service;

import com.currencyconverter.model.User;

/**
 * 
 * @author sandeepkumar
 *
 */
public interface UserService {

	public User saveUser(User user);
	public boolean isUserExists(String emailId);
}
