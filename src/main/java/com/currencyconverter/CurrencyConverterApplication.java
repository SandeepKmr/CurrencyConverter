package com.currencyconverter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.currencyconverter.model.Role;
import com.currencyconverter.model.User;
import com.currencyconverter.repository.UserRepository;
/**
 * 
 * @author sandeepkumar
 *
 */
@SpringBootApplication
@EnableWebSecurity
public class CurrencyConverterApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;

	
	
	public static void main(String[] args) {
		SpringApplication.run(CurrencyConverterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
		
		User obj = new User();
		//obj.setUserName("Sandeep");
		obj.setEmailId("sandeepkumar@live.in");
		obj.setPassword(passwordEncoder.encode("Sandeep@123"));
		obj.setConfirmPassword(passwordEncoder.encode("Sandeep@123"));
		obj.setDateOfBirth(new Date("3/3/2018"));

		Role roleObj = new Role();
		roleObj.setRoleName("ROLE_USER");
		Role roleObj1 = new Role();
		roleObj1.setRoleName("ROLE_ADMIN");
		Set<Role> roleSet = new HashSet();
		roleSet.add(roleObj);
		roleSet.add(roleObj1);
		obj.setRoles(roleSet);
		userRepo.save(obj);

	}
}
