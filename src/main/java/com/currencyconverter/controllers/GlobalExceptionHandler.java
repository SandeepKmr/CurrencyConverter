package com.currencyconverter.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.currencyconverter.model.ExceptionResponse;

/**
 * 
 * @author sandeepkumar
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public ExceptionResponse handleException(HttpServletRequest req, Exception ex) {
		logger.error("Request" + req.getRequestURL() + "threw an exception", ex);
		ExceptionResponse response = new ExceptionResponse();
		response.setCode(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		response.setMessage(ex.getMessage());

		return response;
	}

}
