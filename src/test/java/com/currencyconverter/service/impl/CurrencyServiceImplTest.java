package com.currencyconverter.service.impl;

import com.currencyconverter.model.ConversionQuery;
import com.currencyconverter.model.Currency;
import com.currencyconverter.repository.ConversionQueryRepository;
import com.currencyconverter.repository.CurrencyRepository;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * @author sandeepkumar
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyServiceImplTest {
	
		@MockBean
		private CurrencyRepository currencyRepository;

		@MockBean
		private ConversionQueryRepository conversionQueryRepository;

		@Test
		public void getDataFromApi() {

			TestRestTemplate testRestTemplate = new TestRestTemplate();
			/*
			 * ConversionRates conversionRate = new ConversionRates();
			 * conversionRate.setDisclaimer(
			 * "Usage subject to terms: https://openexchangerates.org/terms");
			 * conversionRate.setLicense("https://openexchangerates.org/license");
			 * 
			 * conversionRate.setTimestamp((long) 1542146400);
			 * conversionRate.setBase("USD");
			 * 
			 * Map<String, BigDecimal> rates = new HashMap<>(); rates.put("INR", new
			 * BigDecimal("72.325")); rates.put("USD", new BigDecimal("1"));
			 * conversionRate.setRates(rates);
			 */
			String uri = "https://openexchangerates.org/api/latest.json?app_id=355d25b401484c2aac9b673f2d788816";
			ResponseEntity<String> result = testRestTemplate.getForEntity(uri, String.class);
			assertEquals(HttpStatus.OK, result.getStatusCode());

		}

		@Test
		public void testGetConversionQueries() {

			List<ConversionQuery> listConversionQuery = new ArrayList<>();
			ConversionQuery conversionQuery1 = new ConversionQuery();
			conversionQuery1.setId(1);
			conversionQuery1.setEmailId("user1@live.in");
			conversionQuery1.setAmount("10");
			conversionQuery1.setFromCurrency("INR");
			conversionQuery1.setToCurrency("EUR");
			conversionQuery1.setConvertedResult("0.12");
			conversionQuery1.setQueryDate(new Date());

			ConversionQuery conversionQuery2 = new ConversionQuery();
			conversionQuery2.setId(2);
			conversionQuery2.setEmailId("user2@gmail.com");
			conversionQuery2.setAmount("20");
			conversionQuery2.setFromCurrency("EUR");
			conversionQuery2.setToCurrency("INR");
			conversionQuery2.setConvertedResult("1635.91");
			conversionQuery2.setQueryDate(new Date());

			listConversionQuery.add(conversionQuery1);
			listConversionQuery.add(conversionQuery2);

			Mockito.when(conversionQueryRepository.findTop10ByEmailIdOrderByQueryDateDesc(Mockito.anyString()))
					.thenReturn(listConversionQuery);
			assertThat(conversionQueryRepository.findTop10ByEmailIdOrderByQueryDateDesc(Mockito.anyString()))
					.isEqualTo(listConversionQuery);
		}

		@Test
		public void testConvertCurrency() {

			String amount = "10";
			String toCurrency = "INR";
			String fromCurrency = "EUR";

			Currency currency1 = new Currency("INR", new BigDecimal(71.234));
			Currency currency2 = new Currency("EUR", new BigDecimal(1.234));
			Currency currency3 = new Currency("AUD", new BigDecimal(2.567));
			Currency currency4 = new Currency("CAD", new BigDecimal(3.234));
			List<Currency> currencyList = new ArrayList<>();
			currencyList.add(currency1);
			currencyList.add(currency2);
			currencyList.add(currency3);
			currencyList.add(currency4);

			BigDecimal conversionAmount = null;

			if (StringUtils.isNotBlank(amount) && StringUtils.isNotBlank(fromCurrency)
					&& StringUtils.isNotBlank(toCurrency)) {

				conversionAmount = new BigDecimal(amount);
				if (conversionAmount.compareTo(BigDecimal.ZERO) > 0) {

					Mockito.when(currencyRepository.findAll()).thenReturn(currencyList);
					Map<String, BigDecimal> currencyRateMap = currencyList.stream()
							.collect(Collectors.toMap(Currency::getCurrencyName, Currency::getRate));

					BigDecimal fromCurrencyValue = currencyRateMap.get(fromCurrency);
					BigDecimal toCurrencyValue = currencyRateMap.get(toCurrency);
					conversionAmount = (toCurrencyValue.divide(fromCurrencyValue, 4, RoundingMode.FLOOR))
							.multiply(conversionAmount);

					System.out.println(conversionAmount);

					assertThat(conversionAmount).isEqualTo(new BigDecimal("577.2600"));

				} else {

					throw new IllegalArgumentException("amount should not be 0 or less than 0");
				}

				ConversionQuery query = new ConversionQuery(1, "user", fromCurrency, toCurrency, amount,
						conversionAmount.toString(), new Date());

				Mockito.when(conversionQueryRepository.save(query)).thenReturn(query);
				assertThat(conversionQueryRepository.save(query)).isEqualTo(query);

			} else {
				throw new IllegalArgumentException("invalid conversion parameters");
			}

		}

		@Test(expected = IllegalArgumentException.class)
		public void testConvertCurrency_InvalidAmount_ExceptionThrown() {

			String amount = "AA";
			String toCurrency = "INR";
			String fromCurrency = "EUR";

			Currency currency1 = new Currency("INR", new BigDecimal(71.234));
			Currency currency2 = new Currency("EUR", new BigDecimal(1.234));
			Currency currency3 = new Currency("AUD", new BigDecimal(2.567));
			Currency currency4 = new Currency("CAD", new BigDecimal(3.234));
			List<Currency> currencyList = new ArrayList<>();
			currencyList.add(currency1);
			currencyList.add(currency2);
			currencyList.add(currency3);
			currencyList.add(currency4);

			BigDecimal conversionAmount = null;

			if (StringUtils.isNotBlank(amount) && StringUtils.isNotBlank(fromCurrency)
					&& StringUtils.isNotBlank(toCurrency)) {

				conversionAmount = new BigDecimal(amount);
				if (conversionAmount.compareTo(BigDecimal.ZERO) > 0) {

					Mockito.when(currencyRepository.findAll()).thenReturn(currencyList);
					Map<String, BigDecimal> currencyRateMap = currencyList.stream()
							.collect(Collectors.toMap(Currency::getCurrencyName, Currency::getRate));

					BigDecimal fromCurrencyValue = currencyRateMap.get(fromCurrency);
					BigDecimal toCurrencyValue = currencyRateMap.get(toCurrency);
					conversionAmount = (toCurrencyValue.divide(fromCurrencyValue, 4, RoundingMode.FLOOR))
							.multiply(conversionAmount);

					System.out.println(conversionAmount);

					assertThat(conversionAmount).isEqualTo(new BigDecimal("577.2600"));

				} else {

					throw new IllegalArgumentException("amount should not be 0 or less than 0");
				}

				ConversionQuery query = new ConversionQuery(1, "user", fromCurrency, toCurrency, amount,
						conversionAmount.toString(), new Date());

				Mockito.when(conversionQueryRepository.save(query)).thenReturn(query);
				assertThat(conversionQueryRepository.save(query)).isEqualTo(query);

			} else {
				throw new IllegalArgumentException("invalid conversion parameters");
			}

		}

		@Test(expected = IllegalArgumentException.class)
		public void testConvertCurrency_AmountZero_ExceptionThrown() {

			String amount = "0";
			String toCurrency = "INR";
			String fromCurrency = "EUR";

			Currency currency1 = new Currency("INR", new BigDecimal(71.234));
			Currency currency2 = new Currency("EUR", new BigDecimal(1.234));
			Currency currency3 = new Currency("AUD", new BigDecimal(2.567));
			Currency currency4 = new Currency("CAD", new BigDecimal(3.234));
			List<Currency> currencyList = new ArrayList<>();
			currencyList.add(currency1);
			currencyList.add(currency2);
			currencyList.add(currency3);
			currencyList.add(currency4);

			BigDecimal conversionAmount = null;

			if (StringUtils.isNotBlank(amount) && StringUtils.isNotBlank(fromCurrency)
					&& StringUtils.isNotBlank(toCurrency)) {

				conversionAmount = new BigDecimal(amount);
				if (conversionAmount.compareTo(BigDecimal.ZERO) > 0) {

					Mockito.when(currencyRepository.findAll()).thenReturn(currencyList);
					Map<String, BigDecimal> currencyRateMap = currencyList.stream()
							.collect(Collectors.toMap(Currency::getCurrencyName, Currency::getRate));

					BigDecimal fromCurrencyValue = currencyRateMap.get(fromCurrency);
					BigDecimal toCurrencyValue = currencyRateMap.get(toCurrency);
					conversionAmount = (toCurrencyValue.divide(fromCurrencyValue, 4, RoundingMode.FLOOR))
							.multiply(conversionAmount);

					System.out.println(conversionAmount);

					assertThat(conversionAmount).isEqualTo(new BigDecimal("577.2600"));

				} else {

					throw new IllegalArgumentException("amount should not be 0 or less than 0");
				}

				ConversionQuery query = new ConversionQuery(1, "user", fromCurrency, toCurrency, amount,
						conversionAmount.toString(), new Date());

				Mockito.when(conversionQueryRepository.save(query)).thenReturn(query);
				assertThat(conversionQueryRepository.save(query)).isEqualTo(query);

			} else {
				throw new IllegalArgumentException("invalid conversion parameters");
			}

		}
	}


