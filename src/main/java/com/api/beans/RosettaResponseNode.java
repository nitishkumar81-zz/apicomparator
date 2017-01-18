package com.api.beans;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class RosettaResponseNode {
	private String processed_sentence;
	private String original_sentence;
	private String normalized_sentence;
	private Map<String, List<String>> tag_dict = new HashMap<>();
	private transient String reprocessed_sentence;
	private Map<String,Integer> tagCountMap;
	

	public String getProcessed_sentence() {
		return processed_sentence;
	}

	public void setProcessed_sentence(String processed_sentence) {
		this.processed_sentence = processed_sentence;
	}
	

	public String getOriginalSentence() {
		return original_sentence;
	}

	public String getNormalizedSentence() {
		return normalized_sentence;
	}

	
	public Map<String, List<String>> getTag_dict() {
		return tag_dict;
	}

	public void setTag_dict(Map<String, List<String>> tag_dict) {
		this.tag_dict = tag_dict;
	}

	
	public RosettaResponseNode(String input) {
		// TODO Auto-generated constructor stub
		this.processed_sentence = input;
		this.normalized_sentence = input;
		this.original_sentence = input;
		this.tag_dict = new HashMap<>();
	}
	 
	
	
	

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public String getReprocessed_sentence() {
		return reprocessed_sentence;
	}

	public void setReprocessed_sentence(String reprocessed_sentence) {
		this.reprocessed_sentence = reprocessed_sentence;
	}

	public Map<String, Integer> getTagCountMap() {
		return tagCountMap;
	}

	public void setTagCountMap(Map<String, Integer> tagCountMap) {
		this.tagCountMap = tagCountMap;
	}
	
}
