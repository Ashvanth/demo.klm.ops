package com.demo.klm.ops.demo.klm.ops;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

import com.demo.klm.ops.demo.klm.ops.h2.dao.StockPriceJdbcRepository;

@SpringBootApplication
@EnableOAuth2Sso
public class Application implements CommandLineRunner{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    StockPriceJdbcRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		// logger.info("Stock id 10001 -> {}", repository.findAll());
	}
}
