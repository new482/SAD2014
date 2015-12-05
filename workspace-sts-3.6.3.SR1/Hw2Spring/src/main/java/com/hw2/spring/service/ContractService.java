package com.hw2.spring.service;

import com.hw2.spring.domain.Contract;
import com.hw2.spring.domain.Product;

public interface ContractService {
	public Contract findContract(long id);
	public long createContract(Product product);
	public long addContractRevenueRecognition(long contractID, double amount);
	public long calculateContractRecognition(long contractID);
}
