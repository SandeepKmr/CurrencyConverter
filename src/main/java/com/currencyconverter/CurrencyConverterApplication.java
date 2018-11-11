package com.currencyconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
/**
 * 
 * @author sandeepkumar
 *
 */
@SpringBootApplication
@EnableWebSecurity
public class CurrencyConverterApplication  {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConverterApplication.class, args);
	}

	
}
