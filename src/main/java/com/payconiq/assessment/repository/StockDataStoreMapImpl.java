package com.payconiq.assessment.repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.payconiq.assessment.beans.Stock;

@Component
public class StockDataStoreMapImpl implements StockDataStore{
	
	private Logger logger = LoggerFactory.getLogger(StockDataStoreMapImpl.class);
	
	private Map<Integer,Stock> stockDataStorage;
	
	public void loadStockData() {
		
		logger.info("Loading the stocks data into data store.");
		
		stockDataStorage = new HashMap<>();
		
		Stock nasdaqStockData   = new Stock(1,  "Nasdaq"  , new BigDecimal(977.65).setScale(2, RoundingMode.CEILING) , Timestamp.valueOf(LocalDateTime.now()));
		Stock teslaStockData    = new Stock(2,  "Tesla"   , new BigDecimal(300.45).setScale(2, RoundingMode.CEILING) , Timestamp.valueOf(LocalDateTime.now()));
		Stock nvidiaStockData   = new Stock(3,  "Nvidia"  , new BigDecimal(700).setScale(2, RoundingMode.CEILING)    , Timestamp.valueOf(LocalDateTime.now()));
		Stock sgxStockData      = new Stock(4,  "SGX"     , new BigDecimal(560.30).setScale(2, RoundingMode.CEILING) , Timestamp.valueOf(LocalDateTime.now()));
		Stock yahooStockData    = new Stock(5,  "Yahoo"   , new BigDecimal(800.20).setScale(2, RoundingMode.CEILING) , Timestamp.valueOf(LocalDateTime.now()));
		Stock verizonStockData  = new Stock(6,  "Verizon" , new BigDecimal(346.25).setScale(2, RoundingMode.CEILING) , Timestamp.valueOf(LocalDateTime.now()));
		Stock alibabaStockData  = new Stock(7,  "Alibaba" , new BigDecimal(876).setScale(2, RoundingMode.CEILING)    , Timestamp.valueOf(LocalDateTime.now()));
		Stock tataStockData     = new Stock(8,  "Tata"    , new BigDecimal(657).setScale(2, RoundingMode.CEILING)    , Timestamp.valueOf(LocalDateTime.now()));
		Stock relianceStockData = new Stock(9,  "Reliance", new BigDecimal(387.40).setScale(2, RoundingMode.CEILING) , Timestamp.valueOf(LocalDateTime.now()));
		Stock axisStockData     = new Stock(10, "Axis"    , new BigDecimal(777.15).setScale(2, RoundingMode.CEILING) , Timestamp.valueOf(LocalDateTime.now()));
		
		stockDataStorage.put(nasdaqStockData.getId(), nasdaqStockData);
		stockDataStorage.put(teslaStockData.getId(), teslaStockData);
		stockDataStorage.put(nvidiaStockData.getId(), nvidiaStockData);
		stockDataStorage.put(sgxStockData.getId(), sgxStockData);
		stockDataStorage.put(yahooStockData.getId(), yahooStockData);
		stockDataStorage.put(verizonStockData.getId(), verizonStockData);
		stockDataStorage.put(alibabaStockData.getId(), alibabaStockData);
		stockDataStorage.put(tataStockData.getId(), tataStockData);
		stockDataStorage.put(relianceStockData.getId(), relianceStockData);
		stockDataStorage.put(axisStockData.getId(), axisStockData);
		
		logger.info("Data Store is loaded with available Stocks.");
	}
	
	public Map<Integer, Stock> getStockDataStoreMap() {
		return this.stockDataStorage;
	}

	public Object getAllAvailableStocks() {
		return stockDataStorage.values();
	}

	public Stock getStockById(int stockId) {
		return stockDataStorage.get(stockId);
	}

	public Stock updateStockPrice(Stock stock) {
		stockDataStorage.put(stock.getId(), stock);
		return stock;
	}

	public Stock addNewStock(Stock stock) {
		stockDataStorage.put(stock.getId(), stock);
		return stock;
	}

}
