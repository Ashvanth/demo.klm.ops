package com.demo.klm.ops.demo.klm.ops.h2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.demo.klm.ops.demo.klm.ops.pojo.StockPrice;

@Repository
public class StockPriceJdbcRepository {
	
	 @Autowired
	    JdbcTemplate jdbcTemplate;
	 
	 class StockRowMapper implements RowMapper<StockPrice>{

		@Override
		public StockPrice mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			
			StockPrice stockPriceObj = new StockPrice();
			stockPriceObj.setId(rs.getLong("id"));
			stockPriceObj.setClose(rs.getDouble("close"));
			stockPriceObj.setHigh(rs.getDouble("high"));
			stockPriceObj.setLow(rs.getDouble("low"));
			stockPriceObj.setOpen(rs.getDouble("open"));
			return stockPriceObj;
		}
		 
	 }
	 
	 public List < StockPrice > findAll() {
	        return jdbcTemplate.query("select * from stockprice", new StockRowMapper());
	    }
	 
	public StockPrice findById() {	
		    /*return jdbcTemplate.queryForObject("select * from stockprice", new Object[] {
		            
		        },
		        new BeanPropertyRowMapper < StockPrice > (StockPrice.class));*/
		return null;
		       
		}
	 
	 public int insert(StockPrice stockPrice) {
	        return jdbcTemplate.update("insert into student (id, open, high,low,close) " + "values(?,  ?, ?)",
	            new Object[] {
	        		stockPrice.getId(), stockPrice.getClose(), stockPrice.getLow()
	            });
	    }

}
