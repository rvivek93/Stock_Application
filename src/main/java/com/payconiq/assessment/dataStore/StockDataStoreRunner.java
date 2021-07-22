package com.payconiq.assessment.dataStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.payconiq.assessment.repository.StockDataStore;

@Component
public class StockDataStoreRunner implements CommandLineRunner {
	
	@Autowired
	private StockDataStore stockDataStoreMapImpl;
	
	@Override
	public void run(String... args) throws Exception {
		stockDataStoreMapImpl.loadStockData();
	}

}
