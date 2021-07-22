package com.payconiq.assessment.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	public List<Stock> getAllAvailableStocks() {
		logger.info("Processing data from the datastore.");
		Map<Integer, Stock> stockDetailsRepository = stockDataStore.getStockDataStoreMap();
		List<Stock> stocksList = new ArrayList<>();
		stocksList.addAll(stockDetailsRepository.values());
		return stocksList;
	}

	public Stock getStockDetails(Integer stockId) {
		logger.info("Processing data from the datastore for Stock " + stockId + ".");
		Map<Integer, Stock> stockDetailsRepository = stockDataStore.getStockDataStoreMap();
		Stock stockDetails = stockDetailsRepository.get(stockId);
		return stockDetails;
	}

	public Stock updateStockPrice(Integer stockId, BigDecimal stockPrice) {
		logger.info("Processing data from the datastore for Stock " + stockId + ".");
		Map<Integer, Stock> stockDetailsRepository = stockDataStore.getStockDataStoreMap();
		Stock stockDetails = stockDetailsRepository.get(stockId);
		if (stockDetails != null) {
			stockDetails.setCurrentPrice(stockPrice.setScale(2, RoundingMode.CEILING));
			stockDetails.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
			stockDetailsRepository.put(stockId, stockDetails);
		}
		return stockDetails;
	}

	public Stock addNewStock(Stock stockProps) {
		logger.info("Processing data from the datastore.");
		Map<Integer, Stock> stockDetailsRepository = stockDataStore.getStockDataStoreMap();
		if (stockDetailsRepository.containsKey(stockProps.getId())) {
			return null;
		}
		stockProps.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
		stockProps.setCurrentPrice(stockProps.getCurrentPrice().setScale(2, RoundingMode.CEILING));
		stockDetailsRepository.put(stockProps.getId(), stockProps);
		return stockDetailsRepository.get(stockProps.getId());
	}

}
