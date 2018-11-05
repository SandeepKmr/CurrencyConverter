package com.currencyconverter.model;

import java.math.BigDecimal;

/**
 * 
 * @author sandeepkumar
 *
 */
public class Currency {

	private String currencyName;
	private BigDecimal rate;

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

}
