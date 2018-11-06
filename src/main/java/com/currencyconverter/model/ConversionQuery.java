package com.currencyconverter.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author sandeepkumar
 *
 */
@Entity
@Table(name = "conversion_query")
public class ConversionQuery {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "query_id")
	private long Id;

	@Column(name = "emai_id")
	private String emailId;

	@Column(name = "from_currency")
	private String fromCurrency;

	@Column(name = "to_Currency")
	private String toCurrency;

	@Column(name = "amount")
	private String amount;

	@Column(name = "converted_result")
	private String convertedResult;

	@Column(name = "query_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date query_date;

	public ConversionQuery() {
	}

	public ConversionQuery(String emailId, String fromCurrency, String toCurrency, String amount,
			String convertedResult, Date query_date) {
		super();
		this.emailId = emailId;
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.amount = amount;
		this.convertedResult = convertedResult;
		this.query_date = query_date;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getConvertedResult() {
		return convertedResult;
	}

	public void setConvertedResult(String convertedResult) {
		this.convertedResult = convertedResult;
	}

	public Date getQuery_date() {
		return query_date;
	}

	public void setQuery_date(Date query_date) {
		this.query_date = query_date;
	}

}
