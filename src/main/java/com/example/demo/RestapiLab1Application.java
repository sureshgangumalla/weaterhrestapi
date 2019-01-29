package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestapiLab1Application {

	public static void main(String[] args) {
		SpringApplication.run(RestapiLab1Application.class, args);
		WeatherAPI weatherAPI = new WeatherAPI();
		weatherAPI.getWeather("Halifax");
	}

}

