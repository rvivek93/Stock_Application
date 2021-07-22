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

		Mockito.when(stockDataStore.getStockDataStoreMap()).thenReturn(stockDetailsRepository);

		List<Stock> expectedAvailableStocks = new ArrayList<>();
		expectedAvailableStocks.addAll(stockDetailsRepository.values());

		List<Stock> actualAvailableStocks = stockService.getAllAvailableStocks();
		assertEquals(expectedAvailableStocks, actualAvailableStocks);
	}

	@Test
	public void testGetStockDetails() {

		Mockito.when(stockDataStore.getStockDataStoreMap()).thenReturn(stockDetailsRepository);

		Stock expectedStockDetails = stockDetailsRepository.get(1);

		Stock actualStockDetails = stockService.getStockDetails(1);

		assertEquals(expectedStockDetails, actualStockDetails);
	}

	@Test
	public void testUpdateStockPrice() {

		Mockito.when(stockDataStore.getStockDataStoreMap()).thenReturn(stockDetailsRepository);

		Stock expectedStockDetails = stockDetailsRepository.get(1);

		Stock actualStockDetails = stockService.updateStockPrice(1, new BigDecimal(520.50));

		assertEquals(expectedStockDetails, actualStockDetails);
	}

	@Test
	public void testAddNewStock() {

		Mockito.when(stockDataStore.getStockDataStoreMap()).thenReturn(stockDetailsRepository);

		Stock expectedStockDetails = new Stock(4, "Zomato", new BigDecimal(500.50),
				Timestamp.valueOf(LocalDateTime.now()));

		Stock actualStockDetails = stockService.addNewStock(expectedStockDetails);

		assertEquals(expectedStockDetails, actualStockDetails);

	}

}
