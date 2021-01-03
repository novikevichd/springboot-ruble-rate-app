package com.example.rublerateapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RubleRateApplication {

	public static void main(String[] args) {
		SpringApplication.run(RubleRateApplication.class, args);
	}

}
