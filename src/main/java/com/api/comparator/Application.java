package com.api.comparator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.api.beans.RosettaResponseArray;
import com.google.gson.Gson;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@ConfigurationProperties(locations = "classpath:api.properties")
public class Application implements CommandLineRunner {
	private String oldUrl;
	private String newUrl;

	@Override
	public void run(String... args) throws Exception {
		Gson gson = new Gson();
		String fileName = args[0];
		String outputFile = args[1];
		Set<String> dataSet = ApiTest.loadFromXLToLanguageMap(fileName);
		Map<String, List<String>> map = new HashMap<>();
		for (String data : dataSet) {
			String newResponse = ApiHandler.handle(newUrl, data);
			String oldResponse = ApiHandler.handle(oldUrl, data);
			RosettaResponseArray newresponseArray = gson.fromJson(newResponse, RosettaResponseArray.class);
			RosettaResponseArray oldresponseArray = gson.fromJson(oldResponse, RosettaResponseArray.class);     
			long start = System.currentTimeMillis();
			Map newMap = gson.fromJson(newResponse, Map.class);
			long newEnd = System.currentTimeMillis();
			Map oldMap = gson.fromJson(oldResponse, Map.class);
			long oldEnd = System.currentTimeMillis();
			
            String newSentence = newresponseArray.getProcessed_text().get(0).getSentences().get(0).getProcessed_sentence();
            String oldSentence = oldresponseArray.getProcessed_text().get(0).getSentences().get(0).getProcessed_sentence();
            
			if (!oldSentence.equals(newSentence)) {
				System.out.println(oldSentence);
				System.out.println(newSentence);
				List<String> strings = new ArrayList<>();
				strings.add(newSentence);
				strings.add(String.valueOf(newEnd - start));
				strings.add(oldSentence);
				strings.add(String.valueOf(oldEnd - newEnd));
				map.put(data, strings);
			}

		}
		ExcelWriter.writeData(outputFile, map);
	}

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Application.class, args);
	}

	public String getOldUrl() {
		return oldUrl;
	}

	public void setOldUrl(String oldUrl) {
		this.oldUrl = oldUrl;
	}

	public String getNewUrl() {
		return newUrl;
	}

	public void setNewUrl(String newUrl) {
		this.newUrl = newUrl;
	}

}
