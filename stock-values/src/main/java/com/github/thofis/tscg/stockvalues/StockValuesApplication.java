package com.github.thofis.tscg.stockvalues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StockValuesApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockValuesApplication.class, args);
	}

}
