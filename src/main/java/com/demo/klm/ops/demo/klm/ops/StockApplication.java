package com.demo.klm.ops.demo.klm.ops;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.klm.ops.demo.klm.ops.h2.dao.StockPriceJdbcRepositoryDAO;

@SpringBootApplication
public class StockApplication implements CommandLineRunner{
	
	
	@Autowired
    StockPriceJdbcRepositoryDAO repository;

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}
}
