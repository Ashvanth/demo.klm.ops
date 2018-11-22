package com.demo.klm.ops.demo.klm.ops;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.klm.ops.demo.klm.ops.h2.dao.StockPriceJdbcRepository;

@SpringBootApplication
public class Application implements CommandLineRunner{
	
	
	@Autowired
    StockPriceJdbcRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}
}
