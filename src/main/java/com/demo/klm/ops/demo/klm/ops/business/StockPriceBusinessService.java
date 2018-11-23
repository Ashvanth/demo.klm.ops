package com.demo.klm.ops.demo.klm.ops.business;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.klm.ops.demo.klm.ops.h2.dao.StockPriceJdbcRepositoryDAO;
import com.demo.klm.ops.demo.klm.ops.model.StockPrice;
import com.demo.klm.ops.security.ResponseModel;

@Service
public class StockPriceBusinessService {
	
	@Autowired
	StockPriceJdbcRepositoryDAO jdbcRep;
	
	private Double costpriceGlobalValue= 0.0;
	private double avgCostPrice ;
	private	double costPriceValue;

	
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
	
	public ResponseModel getCostByYearAndMonth(String date)
	{
		int numberOfData = 0 ;
		List<StockPrice> stockPriceObj = new ArrayList<StockPrice>();
		ResponseModel respModelObj = new ResponseModel();
		List<ResponseModel> responseModelList = new ArrayList<ResponseModel>();
		 stockPriceObj = jdbcRep.fetchCostByYearAndMonth(date);
		 numberOfData = stockPriceObj.size();
		 if(numberOfData>0)
		 {
		 respModelObj = calculateSumAndAvgOfCloseRate(stockPriceObj,numberOfData);
		 }
		 return respModelObj;
	}
	
	
	public ResponseModel getCostByPeriod(String timeValue)
	{
		int numberOfData =0;
		List<StockPrice> stockPriceObj = new ArrayList<StockPrice>();
		ResponseModel respModelObj = new ResponseModel();
		stockPriceObj =jdbcRep.getCostByTime(timeValue);
		if(stockPriceObj!=null && stockPriceObj.size()>0)
		{
		numberOfData = stockPriceObj.size();
		respModelObj = calculateSumAndAvgOfCloseRate(stockPriceObj,numberOfData);
		}
		else
		{
			return respModelObj;
		}
		
		
		return respModelObj;
	}
	
	public ResponseModel calculateSumAndAvgOfCloseRate(List<StockPrice> stockPriceList,int volumeOfData)
	{
		
		
		ResponseModel responseModel = new ResponseModel();
		for(StockPrice costPrice : stockPriceList)
		{
			costPriceValue = costPrice.getClose() ;
			costpriceGlobalValue = costpriceGlobalValue+costPriceValue;
		}
		responseModel.setSumOfCostPrice(costpriceGlobalValue);
		try
		{
		avgCostPrice = costpriceGlobalValue/volumeOfData;
		if(avgCostPrice>0.0)
		{
			responseModel.setAvgOfCosePrice(avgCostPrice);
		}
		
		}
		catch(ArithmeticException e)
		{
			
		}
		return responseModel;
	}
	
}
