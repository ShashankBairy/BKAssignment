package com.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Sample1Application {

	public static void main(String[] args) {
		SpringApplication.run(Sample1Application.class, args);
	}

}
