package com.demo.klm.ops.demo.klm.ops.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.klm.ops.demo.klm.ops.business.StockPriceBusinessService;
import com.demo.klm.ops.demo.klm.ops.h2.dao.StockPriceJdbcRepository;
import com.demo.klm.ops.demo.klm.ops.pojo.StockPrice;

@RestController
public class StockPriceController {
	
	@Autowired
	StockPriceJdbcRepository jdbcRep;
	
	@Autowired
	StockPriceBusinessService businessService;
	
	@RequestMapping(method=RequestMethod.GET,path="/getStocks")
	public List<StockPrice> getStockPrice()
	{
		return jdbcRep.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/insertStocks")
	public void insertStockPrices() throws ClassNotFoundException, IOException
	{
		businessService.insertStockPrince();
	}

}
