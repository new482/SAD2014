package com.hw2.spring.domain;

public class CompleteRecognitionStrategy extends RecognitionStrategy {
	
	void calculateRevenueRecognitions(Contract contract) {
	      contract.addRevenueRecognition(new RevenueRecognition(contract.getRevenue(), contract.getWhenSigned()));
	}
}
