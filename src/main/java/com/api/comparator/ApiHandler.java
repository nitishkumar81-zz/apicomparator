package com.api.comparator;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ApiHandler {
	public static String handle(String url,String payload){
		try {
			HttpResponse<String> response = Unirest.post(url)
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .body(payload)
					  .asString();
			return response.getBody();
		} catch (UnirestException e) {
			
		}
		return null;
	}

	

}
