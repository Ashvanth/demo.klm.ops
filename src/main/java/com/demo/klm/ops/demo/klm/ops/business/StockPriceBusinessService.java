package com.demo.klm.ops.demo.klm.ops.business;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.klm.ops.demo.klm.ops.h2.dao.StockPriceJdbcRepository;
import com.demo.klm.ops.demo.klm.ops.model.StockPrice;

@Service
public class StockPriceBusinessService {
	
	@Autowired
	StockPriceJdbcRepository jdbcRep;

	@SuppressWarnings("null")
	public StockPrice insertStockPrince() throws IOException,ClassNotFoundException
	{
		StockPrice stockPrice=null;
		try {
            BufferedReader bReader = new BufferedReader(new FileReader("C:\\Users\\1565359\\Documents\\SpringBootSampleCode\\KLMProject\\F.csv"));

            while (bReader != null) {
                String read;
                try {
                    read = bReader.readLine();
                    if (read != null) 
                    {
                        String[] array = read.split(",+");
                        for(String result:array)
                        {
                            System.out.println(result);
                        }
                    } 
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                finally
                {
                   if (bReader == null) 
                    {
                        bReader.close();
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
		return stockPrice;
	}
	
	public Double getCostByDate(String date) throws ParseException
	{
		StockPrice stockPriceObj = new StockPrice();
		 Double closeRate = null;
		stockPriceObj = jdbcRep.fetchCostByDate(date);
		
		//to get close rate from the list 
		if(stockPriceObj!=null )
		{
		closeRate =stockPriceObj.getClose();
		}
		return closeRate;
		
	}
	
	
}
