package com.currencyconverter.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author sandeepkumar
 *
 */
@Entity
@Table(name = "currency_rates")
public class Currency {

	@Id
	@Column(name = "currency_name")
	private String currencyName;

	@Column(name = "rate" ,precision = 10, scale = 6)
	private BigDecimal rate;

	public Currency() {

	}

	public Currency(String currencyName, BigDecimal rate) {
		this.currencyName = currencyName;
		this.rate = rate;
	}

	

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
