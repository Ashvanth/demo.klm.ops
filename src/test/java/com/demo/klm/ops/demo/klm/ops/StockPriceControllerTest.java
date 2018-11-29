package com.demo.klm.ops.demo.klm.ops;

import java.text.ParseException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.klm.ops.demo.klm.ops.controller.StockPriceController;
import com.demo.klm.ops.demo.klm.ops.model.StockPrice;
import com.demo.klm.ops.demo.klm.ops.service.StockPriceBusinessService;
import com.demo.klm.ops.security.ResponseModel;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StockPriceControllerTest {

	
	   private StockPriceController stockPriceController= new StockPriceController();
	
	private StockPriceBusinessService mockBusinessService= Mockito.mock(StockPriceBusinessService.class);
	
	
	
	@Test
	public void closeRateOverTime() throws ParseException
	{
		StockPrice stockPriceObj  = new StockPrice();
		stockPriceObj.setId((long) 1);
		stockPriceObj.setClose(2.15322);
		stockPriceObj.setLow(2.149165);
		stockPriceObj.setHigh(2.173495);
		stockPriceObj.setOpen(2.149165);
		Mockito.when(mockBusinessService.closeRateOverTime("6-1-1972")).thenReturn(stockPriceObj);
		StockPrice stockPriceControllerObj = stockPriceController.closeRateOverTime("6-1-1972");
		Assert.assertEquals(stockPriceControllerObj, stockPriceObj);
		
		
	}
	
	@Test
	public void averageCloseRateOverPeriod() 
	{
		ResponseModel responseModelObj  = new ResponseModel();
		responseModelObj.setAvgOfCosePrice(619.811699599);
		ResponseModel respObj =	mockBusinessService.averageCloseRateOverPeriod("6/1999");
		Assert.assertEquals(Double.toString(respObj.getAvgOfCosePrice()), Double.toString(responseModelObj.getAvgOfCosePrice()));
	}

}
