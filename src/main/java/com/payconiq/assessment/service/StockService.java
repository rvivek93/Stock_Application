package com.payconiq.assessment.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.payconiq.assessment.beans.Stock;

@Service
public interface StockService {

	public List<Stock> getAllAvailableStocks();

	public Stock getStockDetails(Integer stockId);

	public Stock updateStockPrice(Integer stockId, BigDecimal stockPrice);

	public Stock addNewStock(Stock stockProps);

}
