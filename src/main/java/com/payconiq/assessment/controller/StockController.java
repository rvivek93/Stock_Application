package com.payconiq.assessment.controller;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payconiq.assessment.beans.Stock;
import com.payconiq.assessment.exception.ResourceAlreadyExistsException;
import com.payconiq.assessment.exception.ResourceNotFoundException;
import com.payconiq.assessment.service.StockService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/stocks")
public class StockController {

	Logger logger = LoggerFactory.getLogger(StockController.class);

	@Autowired
	private StockService stockService;

	@GetMapping(path = "")
	@ApiOperation(value = "Get All stocks")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<Object> getAllStocks() {
		List<Stock> stockList = null;
		try {
			logger.info("Request to get all the stocks.");
			stockList = stockService.getAllAvailableStocks();
			logger.info("Displaying the available stocks.");
		} catch (Exception e) {
			System.out.println("Error while fetching Stocks." + e);
		}
		return ResponseEntity.status(HttpStatus.OK).body(stockList);
	}

	@GetMapping(path = "/{stockId}")
	@ApiOperation(value = "Get Stock details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public ResponseEntity<Object> getStockDetails(@PathVariable Integer stockId) {
		logger.info("Request to get the stock detail.");
		Stock stockDetails = stockService.getStockDetails(stockId);
		logger.info("Displaying the stock detail.");
		if (stockDetails == null) {
			throw new ResourceNotFoundException("No Stock Details found for Id : " + stockId);
		}
		return ResponseEntity.status(HttpStatus.OK).body(stockDetails);
	}

	@PutMapping(path = "/{stockId}/{stockPrice}")
	@ApiOperation(value = "Update the Stock Price")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Object.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public ResponseEntity<Object> updateStockPrice(@PathVariable Integer stockId, @PathVariable BigDecimal stockPrice) {
		logger.info("Request to update the stock price.");
		Stock stockDetails = stockService.updateStockPrice(stockId, stockPrice);
		logger.info("Displaying the updated stock price.");
		if (stockDetails == null) {
			throw new ResourceNotFoundException("No Stock Details found for Id : " + stockId + ". Cannot update.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(stockDetails);
	}

	@PostMapping(path = "")
	@ApiOperation(value = "Add a new Stock")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public ResponseEntity<Object> addNewStock(@RequestBody Stock stock) {
		logger.info("Request to add a new stock.");
		Stock stockDetails = null;
		stockDetails = stockService.addNewStock(stock);
		if (stockDetails == null) {
			throw new ResourceAlreadyExistsException(
					"The Stock Id " + stock.getId() + " already exists. Please provide a new unique Id.");
		}
		logger.info("New Stock Created.");
		return ResponseEntity.status(HttpStatus.CREATED).body(stockDetails);
	}

}
