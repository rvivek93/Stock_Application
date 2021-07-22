package com.payconiq.assessment.repository;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.payconiq.assessment.beans.Stock;

@Repository
public interface StockDataStore {
	
	public Map<Integer, Stock> getStockDataStoreMap();
	
	public void loadStockData();

}
