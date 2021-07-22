package com.payconiq.assessment.datastore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.payconiq.assessment.repository.StockDataStoreMapImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class StockDataStoreTest {

	@InjectMocks
	private StockDataStoreMapImpl stockDataStoreMapImpl;
	

	@Test
	public void testLoadData() {
		stockDataStoreMapImpl.loadStockData();

		assertEquals(10, stockDataStoreMapImpl.getStockDataStoreMap().size());
	}

}
