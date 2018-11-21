package com.demo.klm.ops.demo.klm.ops.h2.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.demo.klm.ops.demo.klm.ops.pojo.StockPrice;

@Repository
public class StockPriceJdbcRepository {
	
	 @Autowired
	    JdbcTemplate jdbcTemplate;
	 
	 @SuppressWarnings("unchecked")
	public List<StockPrice> findById() {	
		    return (List<StockPrice>) jdbcTemplate.queryForObject("select * from stockprice", new Object[] {
		            
		        },
		        new BeanPropertyRowMapper < StockPrice > (StockPrice.class));
		       
		}
	 
	 public int insert(StockPrice stockPrice) {
	        return jdbcTemplate.update("insert into student (id, open, high,low,close) " + "values(?,  ?, ?,?)",
	            new Object[] {
	        		stockPrice.getId(), stockPrice.getClose(), stockPrice.getLow()
	            });
	    }

}
