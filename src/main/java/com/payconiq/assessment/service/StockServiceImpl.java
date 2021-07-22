package com.payconiq.assessment.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payconiq.assessment.beans.Stock;
import com.payconiq.assessment.repository.StockDataStore;

@Service
public class StockServiceImpl implements StockService {

	Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

	@Autowired
	private StockDataStore stockDataStore;

	@SuppressWarnings("unchecked")
	public List<Stock> getAllAvailableStocks() {
		logger.info("Processing data from the datastore.");
		List<Stock> availableStocks = new ArrayList<>();
		availableStocks.addAll((Collection<? extends Stock>) stockDataStore.getAllAvailableStocks());
		return availableStocks;
	}

	public Stock getStockDetails(Integer stockId) {
		logger.info("Processing data from the datastore for Stock " + stockId + ".");
		return stockDataStore.getStockById(stockId);
	}

	public Stock updateStockPrice(Integer stockId, BigDecimal stockPrice) {
		logger.info("Processing data from the datastore for Stock " + stockId + ".");
		Stock stock = stockDataStore.getStockById(stockId);
		if (stock == null) {
			return null;
		}
		stock.setCurrentPrice(stockPrice.setScale(2, RoundingMode.CEILING));
		stock.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
		return stockDataStore.updateStockPrice(stock);
	}

	public Stock addNewStock(Stock stock) {
		logger.info("Processing data from the datastore.");
		Stock stockData = stockDataStore.getStockById(stock.getId());
		if (stockData != null) {
			return null;
		}
		stock.setCurrentPrice(stock.getCurrentPrice().setScale(2, RoundingMode.CEILING));
		stock.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
		return stockDataStore.addNewStock(stock);
	}

}
