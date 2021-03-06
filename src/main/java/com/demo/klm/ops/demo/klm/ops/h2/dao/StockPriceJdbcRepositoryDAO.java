package com.demo.klm.ops.demo.klm.ops.h2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.demo.klm.ops.demo.klm.ops.model.StockPrice;

@Repository
public class StockPriceJdbcRepositoryDAO {
	
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
			stockPriceObj.setDate(rs.getString("date"));
			return stockPriceObj;
		}
		 
	 }
	 
	 public List < StockPrice > findAll() {
	        return jdbcTemplate.query("select * from stockprice", new StockRowMapper());
	    }
	 
	
	 
	 public int insert(StockPrice stockPrice) {
	        return jdbcTemplate.update("insert into student (id, open, high,low,close) " + "values(?,  ?, ?)",
	            new Object[] {
	        		stockPrice.getId(), stockPrice.getClose(), stockPrice.getLow()
	            });
	    }

	 
	 @SuppressWarnings("unchecked")
	public StockPrice fetchCostByDate(String date) throws ParseException
	 {
		 StockPrice stockPriceobj = new StockPrice();
		
		 stockPriceobj=   jdbcTemplate.queryForObject("select * from stockprice where date=?", new Object[] {
		            
				 date },
	        new BeanPropertyRowMapper < StockPrice>  (StockPrice.class));
		
		 return stockPriceobj;
	
		
	 }
	 
	 public List<StockPrice> getCostByTime(String inputValue)
	 {
		List <StockPrice> stockPriceobj = new ArrayList<StockPrice>();
		if(inputValue!=null)
		{
		if(!inputValue.startsWith("/"))
		{
			stockPriceobj=	 jdbcTemplate.query("select * from stockprice where date like ?", new Object[] {
		            
					 inputValue+"%" },   new BeanPropertyRowMapper < StockPrice>  (StockPrice.class));
			
		}
		else
		{
		stockPriceobj=	 jdbcTemplate.query("select * from stockprice where date like ?", new Object[] {
		            
				 "%"+inputValue+"%" },   new BeanPropertyRowMapper < StockPrice>  (StockPrice.class));
				}
		return stockPriceobj;
	 }
		else
		{
			return stockPriceobj;
		}
	 }
	 
	 public List<StockPrice> fetchCostByYearAndMonth(String inputValue)
	 {
		 List <StockPrice> stockPriceobj = new ArrayList<StockPrice>();
		 
		 
		 stockPriceobj=	 jdbcTemplate.query("select * from stockprice where date like ?", new Object[] {
		            
				 inputValue },   new BeanPropertyRowMapper < StockPrice>  (StockPrice.class));
		 return stockPriceobj;
	 }
	
	 
}
