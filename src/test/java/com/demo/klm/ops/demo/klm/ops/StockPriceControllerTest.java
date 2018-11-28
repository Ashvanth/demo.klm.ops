package com.demo.klm.ops.demo.klm.ops;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.klm.ops.demo.klm.ops.model.StockPrice;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockPriceControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StockPrice stockPriceModelObj;

}
