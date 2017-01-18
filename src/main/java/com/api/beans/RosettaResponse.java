package com.api.beans;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class RosettaResponse {
	private String paragraph;
	private String context;
	private Double context_prob;
	
	private List<RosettaResponseNode> sentences = new ArrayList<>();

	
	public List<RosettaResponseNode> getSentences() {
		return sentences;
	}

	public void setSentences(List<RosettaResponseNode> sentences) {
		this.sentences = sentences;
	}

	public String getParagraph() {
		return paragraph;
	}

	public void setParagraph(String paragraph) {
		this.paragraph = paragraph;
	}
	

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Double getContext_prob() {
		return context_prob;
	}

	public void setContext_prob(Double context_prob) {
		this.context_prob = context_prob;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
	

}