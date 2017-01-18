package com.api.beans;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class RosettaResponseArray {
	private List<RosettaResponse> processed_text = new ArrayList<>();
	
	
	public List<RosettaResponse> getProcessed_text() {
		return processed_text;
	}


	public void setProcessed_text(List<RosettaResponse> processed_text) {
		this.processed_text = processed_text;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	

}
