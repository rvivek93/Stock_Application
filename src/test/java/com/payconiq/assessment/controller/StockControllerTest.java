package com.payconiq.assessment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payconiq.assessment.beans.Stock;
import com.payconiq.assessment.service.StockService;

@SpringBootTest
@AutoConfigureMockMvc
public class StockControllerTest {

	@MockBean
	private StockService stockService;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	public void testGetAllStocks() throws Exception {

		List<Stock> availableStocks = new ArrayList<>();

		Stock nasdaqStockData = new Stock(1, "Nasdaq", new BigDecimal(977.65), Timestamp.valueOf(LocalDateTime.now()));
		Stock teslaStockData = new Stock(2, "Tesla", new BigDecimal(300.49), Timestamp.valueOf(LocalDateTime.now()));
		Stock nvidiaStockData = new Stock(3, "Nvidia", new BigDecimal(700.95), Timestamp.valueOf(LocalDateTime.now()));

		availableStocks.add(nasdaqStockData);
		availableStocks.add(teslaStockData);
		availableStocks.add(nvidiaStockData);

		Mockito.when(stockService.getAllAvailableStocks()).thenReturn(availableStocks);

		MockHttpServletResponse response = mockMvc.perform(get("/stocks").accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		assertEquals(mapper.writeValueAsString(availableStocks), response.getContentAsString());
	}

	@Test
	public void testGetStockDetails() throws Exception {

		Stock nasdaqStockData = new Stock(1, "Nasdaq", new BigDecimal(977.65), Timestamp.valueOf(LocalDateTime.now()));

		Mockito.when(stockService.getStockDetails(1)).thenReturn(nasdaqStockData);

		MockHttpServletResponse response = mockMvc.perform(get("/stocks/1").accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		assertEquals(mapper.writeValueAsString(nasdaqStockData), response.getContentAsString());
	}

	@Test
	public void testUpdateStockDetails() throws Exception {

		Stock nasdaqStockData = new Stock(1, "Nasdaq", new BigDecimal(300.90), Timestamp.valueOf(LocalDateTime.now()));

		Mockito.when(stockService.updateStockPrice(1, new BigDecimal(300.90))).thenReturn(nasdaqStockData);

		MockHttpServletResponse response = mockMvc
				.perform(put("/stocks/{stockId}/{stockPrice}", 1, new BigDecimal(300.90))).andReturn().getResponse();

		assertEquals(mapper.writeValueAsString(nasdaqStockData), response.getContentAsString());
	}

	@Test
	public void testCreateStock() throws Exception {

		Stock nasdaqStockData = new Stock(11, "Zomato", new BigDecimal(300.90), Timestamp.valueOf(LocalDateTime.now()));

		Mockito.when(stockService.addNewStock(Mockito.any())).thenReturn(nasdaqStockData);

		MockHttpServletResponse response = mockMvc.perform(post("/stocks")
				.content(mapper.writeValueAsString(nasdaqStockData)).contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		assertEquals(mapper.writeValueAsString(nasdaqStockData), response.getContentAsString());
	}

}
