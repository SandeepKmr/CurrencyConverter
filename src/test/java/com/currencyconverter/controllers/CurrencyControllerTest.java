package com.currencyconverter.controllers;

import com.currencyconverter.model.ConversionQuery;
import com.currencyconverter.model.Currency;
import com.currencyconverter.service.CurrencyService;
import com.currencyconverter.utility.Mapping;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author sandeepkumar
 *
 */

@WebMvcTest(value = CurrencyController.class)
@RunWith(SpringRunner.class)
public class CurrencyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CurrencyService currencyService;

	@Test
	@WithMockUser(username = "user", password = "password", roles = { "ADMIN" })
	public void testGetLatestCurrencyRates() throws Exception {

		String URI = "/currency/latest-currency-rates";

		Currency currency1 = new Currency("INR", new BigDecimal("72.385046"));
		Currency currency2 = new Currency("EUR", new BigDecimal("0.886485"));
		List<Currency> currencyList = new ArrayList<>();
		currencyList.add(currency1);
		currencyList.add(currency2);
		when(currencyService.getLatestRates()).thenReturn(currencyList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = Mapping.mapToJsonString(currencyList);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}

	@Test
	@WithMockUser(username = "user", password = "password", roles = { "ADMIN" })
	public void testConvertCurrency() throws Exception {

		String URI = "/currency/convert/INR/EUR/10";

		Mockito.when(currencyService.convertCurrency(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
				.thenReturn("0.1220");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedAmount = "0.1220";
		String outputAmount = result.getResponse().getContentAsString();
		assertThat(outputAmount).isEqualTo(expectedAmount);

	}

	@Test
	@WithMockUser(username = "user", password = "password", roles = { "ADMIN" })
	public void getConversionQueries() throws Exception {

		String URI = "/currency/conversion-queries";

		Date cureentDateTime = new Date();
		// Timestamp cureentDateTime = new Timestamp(cureentDateTime);
		ConversionQuery conversionQuery1 = new ConversionQuery(1, "user1@gmail.com", "INR", "EUR", "10", "0.1220",
				cureentDateTime);
		ConversionQuery conversionQuery2 = new ConversionQuery(2, "user2@gmail.com", "INR", "CAD", "10", "0.1820",
				cureentDateTime);
		List<ConversionQuery> coversionQueryList = new ArrayList<>();
		coversionQueryList.add(conversionQuery1);
		coversionQueryList.add(conversionQuery2);

		Mockito.when(currencyService.getConversionQueries(Mockito.anyString())).thenReturn(coversionQueryList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = Mapping.mapToJsonString(coversionQueryList);
		System.out.println("Expected -" + expectedJson);
		String outputInJson = result.getResponse().getContentAsString();
		System.out.println("outputJson -" + outputInJson);
		//assertThat(outputInJson).isEqualTo(expectedJson);

	}

}
