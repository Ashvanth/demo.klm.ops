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
import com.demo.klm.ops.demo.klm.ops.h2.dao.StockPriceJdbcRepository;
import com.demo.klm.ops.demo.klm.ops.model.StockPrice;

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
	
	
	@RequestMapping(method=RequestMethod.GET,value = "/getStocks/{date}")
	public Double getCostByDate(@PathVariable("date") String date ) throws ParseException
	{
		return businessService.getCostByDate(date);
	}
	
	@RequestMapping(method=RequestMethod.GET,value = "/getStocks/query")
	public Double getCostByPeriod(@QueryParam("date") String date,@QueryParam("month") String month,@QueryParam("year") String year) 
	{
		String dateinput=null;
		Double closePrice = null;
		if(date!=null && month!=null && year!=null)
		{
			System.out.println("Into all 3 values method");
			dateinput = month+"-"+date+"-"+year;
			try {
				return businessService.getCostByDate(dateinput);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(date!=null && month!=null && year==null)
		{
			dateinput = month+"-"+date+"-";
			return businessService.getCostByDateAndMonth(dateinput);
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
		closePrice = businessService.getCostByPeriod(dateinput);
		
		return closePrice;

	}


}
