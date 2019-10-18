package com.poc.deliveryapp.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MMR {

	private int estimate;

	public int getEstimate() {
		return estimate;
	}

	public void setEstimate(int estimate) {
		this.estimate = estimate;
	}

}
