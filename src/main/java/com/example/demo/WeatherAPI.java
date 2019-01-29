package com.example.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class WeatherAPI {
	
	String API_KEY = "65e3ad21447caeb2b551513baae9d0df";
	
	
	public static Map<String, Object> jsonToMap(String str){
		Map<String, Object> map = new Gson().fromJson(
				str, new TypeToken<HashMap<String,Object>>() {}.getType()
				);
		return map;
	}
	
	public String[] getWeather(String location) {
		
		String[] retString = new String[4];
		
		String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" +location+"&appid="
				+ this.API_KEY + "&units=imperial";
		
		try {
			StringBuilder weatherResult = new StringBuilder();
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			
			while(null != (line=rd.readLine())) {
				weatherResult.append(line);
			}
			rd.close();
			System.out.println(weatherResult);
			
			Map<String,Object> resMap = jsonToMap(weatherResult.toString());
			Map<String,Object> mainMap = jsonToMap(resMap.get("main").toString());
			Map<String,Object> windMap = jsonToMap(resMap.get("wind").toString());
			
			retString[0] = mainMap.get("temp").toString();
			retString[1] = mainMap.get("humidity").toString();
			retString[2] = mainMap.get("temp_min").toString();
			retString[3] = mainMap.get("temp_max").toString();
			
			
			
			System.out.println("Current Temp: " +mainMap.get("temp"));
			System.out.println("Current Humidity: " +mainMap.get("humidity"));
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return retString;
	}

}
