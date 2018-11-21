package com.demo.klm.ops.demo.klm.ops.business;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.demo.klm.ops.demo.klm.ops.pojo.StockPrice;

@Service
public class StockPriceBusinessService {

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
	
}
