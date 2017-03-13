package com.api.comparator;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiHandler {
	public static String handle(String url,String payload) throws IOException{
		OkHttpClient client = new OkHttpClient();

MediaType mediaType = MediaType.parse("application/json");
RequestBody body = RequestBody.create(mediaType, payload);
Request request = new Request.Builder()
  .url(url)
  .post(body)
  .addHeader("content-type", "application/json")
  .addHeader("cache-control", "no-cache")
  .addHeader("postman-token", "7e57b6cb-8e57-bf1d-7482-4c6564d53b79")
  .build();

Response response = client.newCall(request).execute();
String resp = response.body().string();
return resp;

	}

}
