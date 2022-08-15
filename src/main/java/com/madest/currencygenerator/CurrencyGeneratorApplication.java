package com.madest.currencygenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CurrencyGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyGeneratorApplication.class, args);
		System.out.println();
	}

}
