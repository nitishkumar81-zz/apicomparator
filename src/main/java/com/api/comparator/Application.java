package com.api.comparator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.api.beans.RosettaResponseArray;
import com.api.beans.RosettaResponseNode;
import com.google.gson.Gson;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication

@ConfigurationProperties(locations = "classpath:api.properties")
public class Application implements CommandLineRunner {
	private String oldUrl;
	private String newUrl;

	@Override
	public void run(String... args) throws Exception {
		Gson gson = new Gson();
		String fileName = "/home/devesh/Desktop/request.xlsx";
		String outputFile = "/home/devesh/Desktop/response.xlsx";
		Set<String> dataSet = ApiTest.loadFromXLToLanguageMap(fileName);
		Map<String, List<String>> map = new HashMap<>();
		for (String data : dataSet) {
			long start = System.currentTimeMillis();
			String newResponse = ApiHandler.handle(newUrl, data);
			long newEnd = System.currentTimeMillis();
			String oldResponse = ApiHandler.handle(oldUrl, data);
			long oldEnd = System.currentTimeMillis();

			RosettaResponseArray newresponseArray = gson.fromJson(newResponse, RosettaResponseArray.class);
			RosettaResponseArray oldresponseArray = gson.fromJson(oldResponse, RosettaResponseArray.class);
			if(newresponseArray == null){
				List<String> strings = new ArrayList<>();
				strings.add("Failed");
				//strings.add(String.valueOf(newEnd - start));
				strings.add(oldResponse);
				//strings.add(String.valueOf(oldEnd - newEnd));
				map.put(data, strings);
			}else if(oldresponseArray == null){
				List<String> strings = new ArrayList<>();
				strings.add(newResponse);
				//strings.add(String.valueOf(newEnd - start));
				strings.add("Failed");
				//strings.add(String.valueOf(oldEnd - newEnd));
				map.put(data, strings);
			}else {
				List<String> strings = new ArrayList<>();
				strings.add(newResponse);
				//strings.add(String.valueOf(newEnd - start));
				strings.add(oldResponse);
				//strings.add(String.valueOf(oldEnd - newEnd));
				map.put(data, strings);
			}
			/*if(newresponseArray !=null && oldresponseArray !=null){
				for(int i=0;i<newresponseArray.getProcessed_text().size();i++) {
					List<RosettaResponseNode> nodes = newresponseArray.getProcessed_text().get(i).getSentences();
					if (nodes.size() != oldresponseArray.getProcessed_text().get(i).getSentences().size()) {
						List<String> strings = new ArrayList<>();
						strings.add(newResponse);
						strings.add(String.valueOf(newEnd - start));
						strings.add(oldResponse);
						strings.add(String.valueOf(oldEnd - newEnd));
						map.put(data, strings);
						break;
					}
					for (int j = 0; j < nodes.size(); j++) {
						String newSentence = nodes.get(j).getProcessed_sentence();
						String oldSentence = oldresponseArray.getProcessed_text().get(i).getSentences().get(j).getProcessed_sentence();
						if (!oldSentence.equals(newSentence)) {
							List<String> strings = new ArrayList<>();
							strings.add(newSentence);
							strings.add(String.valueOf(newEnd - start));
							strings.add(oldSentence);
							strings.add(String.valueOf(oldEnd - newEnd));
							map.put(nodes.get(j).getOriginalSentence(), strings);
						}


					}
				}
			}*/

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
