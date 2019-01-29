package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class WeatherService {

	private String city, weatherType;
	private float temperature;

	@RequestMapping(value = "/weather/city", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getWeather() {

		WeatherAPI weatherAPI = new WeatherAPI();
		String[] resString = weatherAPI.getWeather(this.city);
		String json,pattern;

		if(null == resString[0]) {
			pattern = "{\"city\":\"%s\", \"Result\":\"%s\"}";
			json = String.format(pattern,city,"Weather details for this location are not available");
		}else {
			pattern = "{\"city\":\"%s\",\"Temperature\":\"%s\",\"Humidity\":\"%s\",\"Min Temp\":\"%s\",\"Max Temp\":\"%s\"}";
			json = String.format(pattern,city, resString[0], resString[1], resString[2], resString[3]);
		}
		return json;
	}

	@RequestMapping(value = "/weather/city", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public String setCityName(@RequestBody Location location) {
		this.city = location.getCity();
		
		String pattern = "{\"city\":\"%s\"}";
		
		return String.format(pattern,city);
	}

}
