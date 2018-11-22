package com.demo.klm.ops.demo.klm.ops.business;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.klm.ops.demo.klm.ops.h2.dao.StockPriceJdbcRepository;
import com.demo.klm.ops.demo.klm.ops.model.StockPrice;

@Service
public class StockPriceBusinessService {
	
	@Autowired
	StockPriceJdbcRepository jdbcRep;
	
	private Double CostpriceGlobalValue= 0.0;

	
	public Double getCostByDate(String date) throws ParseException
	{
		StockPrice stockPriceObj = new StockPrice();
		 Double closeRate = null;
		 String dateFormat = date.replace("-", "/");
		stockPriceObj = jdbcRep.fetchCostByDate(dateFormat);
		
		//to get close rate from the list 
		if(stockPriceObj!=null )
		{
		closeRate =stockPriceObj.getClose();
		}
		return closeRate;
		
	}
	
	public Double getCostByDateAndMonth(String date)
	{
		List<StockPrice> stockPriceObj = new ArrayList<StockPrice>();
		 Double closeRate ;
		 String dateFormat = date.replace("-", "/");
		 stockPriceObj = jdbcRep.fetchCostByDateAndMonth(dateFormat);
		 closeRate = calculateSumOfCloseRate(stockPriceObj);
		 return closeRate;
	}
	
	
	public Double getCostByPeriod(String timeValue)
	{
		double sumOfCostPriceValue;
		List<StockPrice> stockPriceObj = new ArrayList<StockPrice>();
		stockPriceObj =jdbcRep.getCostByTime(timeValue);
		
		sumOfCostPriceValue = calculateSumOfCloseRate(stockPriceObj);
		
		return sumOfCostPriceValue;
	}
	
	public Double calculateSumOfCloseRate(List<StockPrice> stockPriceList)
	{
		double costPriceValue;
		
		for(StockPrice costPrice : stockPriceList)
		{
			costPriceValue = costPrice.getClose() ;
			CostpriceGlobalValue = CostpriceGlobalValue+costPriceValue;
		}
		return CostpriceGlobalValue;
	}
	
}
