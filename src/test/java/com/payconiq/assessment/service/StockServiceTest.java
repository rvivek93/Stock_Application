package com.payconiq.assessment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.payconiq.assessment.beans.Stock;
import com.payconiq.assessment.repository.StockDataStore;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class StockServiceTest {

	@InjectMocks
	private StockServiceImpl stockService;

	@MockBean
	private StockDataStore stockDataStore;

	private static Map<Integer, Stock> stockDetailsRepository = null;

	@BeforeAll
	public static void initEach() {
		stockDetailsRepository = new HashMap<>();
		Stock nasdaqStockData = new Stock(1, "Nasdaq", new BigDecimal(977.65), Timestamp.valueOf(LocalDateTime.now()));
		Stock teslaStockData = new Stock(2, "Tesla", new BigDecimal(300.49), Timestamp.valueOf(LocalDateTime.now()));
		Stock nvidiaStockData = new Stock(3, "Nvidia", new BigDecimal(700.95), Timestamp.valueOf(LocalDateTime.now()));

		stockDetailsRepository.put(1, nasdaqStockData);
		stockDetailsRepository.put(2, teslaStockData);
		stockDetailsRepository.put(3, nvidiaStockData);

	}

	@Test
	public void testGetAllAvailableStocks() {

		List<Stock> expectedAvailableStocks = new ArrayList<>();
		expectedAvailableStocks.addAll(stockDetailsRepository.values());

		Mockito.when(stockDataStore.getAllAvailableStocks()).thenReturn(expectedAvailableStocks);

		List<Stock> actualAvailableStocks = stockService.getAllAvailableStocks();
		assertEquals(expectedAvailableStocks, actualAvailableStocks);
	}

	@Test
	public void testGetStockDetails() {

		Mockito.when(stockDataStore.getStockById(1)).thenReturn(stockDetailsRepository.get(1));

		Stock expectedStockDetails = stockDetailsRepository.get(1);

		Stock actualStockDetails = stockService.getStockDetails(1);

		assertEquals(expectedStockDetails, actualStockDetails);
	}

	@Test
	public void testUpdateStockPrice() {

		Mockito.when(stockDataStore.getStockById(Mockito.anyInt())).thenReturn(stockDetailsRepository.get(2));

		Mockito.when(stockDataStore.updateStockPrice(Mockito.any())).thenReturn(stockDetailsRepository.get(2));

		Stock expectedStockDetails = stockDetailsRepository.get(2);

		Stock actualStockDetails = stockService.updateStockPrice(1, new BigDecimal(300.49));

		assertEquals(expectedStockDetails, actualStockDetails);
	}

	@Test
	public void testAddNewStock() {

		Stock stock = new Stock(4, "Zomato", new BigDecimal(500.50), Timestamp.valueOf(LocalDateTime.now()));

		stockDetailsRepository.put(4, stock);

		Mockito.when(stockDataStore.addNewStock(Mockito.any())).thenReturn(stockDetailsRepository.get(4));

		Stock expectedStockDetails = stock;

		Stock actualStockDetails = stockService.addNewStock(expectedStockDetails);

		assertEquals(expectedStockDetails, actualStockDetails);

	}

}
