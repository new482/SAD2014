package com.hw2.domain;

public class CompleteRecognitionStrategy extends RecognitionStrategy {
	
	public CompleteRecognitionStrategy(){
		
	}
	
	void calculateRevenueRecognitions(Contract contract) {
	      contract.addRevenueRecognition(new RevenueRecognition(contract.getRevenue(), contract.getWhenSigned()));
	}
}
