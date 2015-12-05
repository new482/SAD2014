package com.hw2.bean;

import javax.ejb.Remote;

import com.hw2.domain.Contract;
import com.hw2.domain.Product;

@Remote
public interface ContractBeanRemote {
	public Contract findContract(long id);

	public long createContract(Product product);

	public long addContractRevenueRecognition(long contractID, double amount);

	public long calculateContractRecognition(long contractID);
	
	public long getContractRevenue(long contractID);
}
