package com.demo.klm.ops.demo.klm.ops.controller;

import java.text.ParseException;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.klm.ops.demo.klm.ops.business.StockPriceBusinessService;
import com.demo.klm.ops.demo.klm.ops.h2.dao.StockPriceJdbcRepositoryDAO;
import com.demo.klm.ops.demo.klm.ops.model.StockPrice;
import com.demo.klm.ops.security.ResponseModel;

@RestController
public class StockPriceController {
	
	@Autowired
	StockPriceJdbcRepositoryDAO jdbcRep;
	
	@Autowired
	StockPriceBusinessService businessService;
	
	@RequestMapping(method=RequestMethod.GET,path="/getStocks")
	public List<StockPrice> getStockPrice()
	{
		return jdbcRep.findAll();
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value = "/getStocks/{date}")
	public Double getCostByDate(@PathVariable("date") String date ) throws ParseException
	{
		return businessService.getCostByDate(date);
	}
	
	@RequestMapping(method=RequestMethod.GET,value = "/getStocks/query")
	public String getCostByPeriod(@QueryParam("date") String date,@QueryParam("month") String month,@QueryParam("year") String year) 
	{
		String dateinput=null;
		Double closePrice = null;
		String sumOfCloseRate=null;
		String avgOfCloseRate = null;
		ResponseModel responseModel = new ResponseModel();
		if(date==null && month==null && year==null)
		{
			return "No input parameter recieved OR Incorrect format ";
		}
		if(date!=null && month!=null && year!=null)
		{
			dateinput = month+"/"+date+"/"+year;
			try {
				closePrice=	businessService.getCostByDate(dateinput);
				return "Sum of close rate calculated for input URL DAY & MONTH & YEAR  "+Double.toString(closePrice);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(  year!=null  && month!=null && date==null)
		{
			dateinput =month+"/%%/"+year;
			responseModel=	businessService.getCostByYearAndMonth(dateinput);
			 sumOfCloseRate = Double.toString(responseModel.getSumOfCostPrice());
			 avgOfCloseRate = Double.toString(responseModel.getAvgOfCosePrice());
			if(sumOfCloseRate!=null && avgOfCloseRate!= null)
			{
			return "Sum of close rate calculated by MONTH & YEAR \n"+sumOfCloseRate +"    \n Avg close rate "+avgOfCloseRate;
			}else
			{
				return "No Close Returned for the YEAR & MONTH";
			}
		}
		
		
		if(date!=null)
		{
			dateinput="/"+date+"/";
		}
		if(month!=null)
		{
			dateinput=month+"/";
		}
		if(year!=null)
		{
			dateinput="/"+year;
		}
		responseModel = businessService.getCostByPeriod(dateinput);
		sumOfCloseRate = Double.toString(responseModel.getSumOfCostPrice());
		 avgOfCloseRate = Double.toString(responseModel.getAvgOfCosePrice());
		if(sumOfCloseRate!=null && avgOfCloseRate!= null)
		{
		return "Sum of close rate calculated by MONTH/ YEAR / DAY "+sumOfCloseRate+"\n" +"   \n Avg close rate "+avgOfCloseRate;
		}else
		{
			return "No Close rate Returned for the YEAR / MONTH / DAY";
		}
		
		

	}


}
