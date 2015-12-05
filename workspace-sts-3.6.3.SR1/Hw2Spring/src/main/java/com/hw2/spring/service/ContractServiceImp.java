package com.hw2.spring.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hw2.spring.domain.Contract;
import com.hw2.spring.domain.Product;
import com.hw2.spring.domain.RevenueRecognition;
import com.hw2.spring.domain.base.MfDate;
import com.hw2.spring.domain.base.Money;

@Service
public class ContractServiceImp implements ContractService {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public Contract findContract(long id) {
		return em.find(Contract.class, id);
	}

	@Transactional
	public long createContract(Product product) {
		Contract contract = new Contract(product, Money.dollars(100),
				MfDate.today());
		em.persist(contract);
		em.flush();
		return contract.getID();
	}

	@Transactional
	public long addContractRevenueRecognition(long contractID, double amount) {
		Contract contract = findContract(contractID);
		contract.addRevenueRecognition(new RevenueRecognition(Money
				.dollars(amount), MfDate.today()));
		em.persist(contract);
		em.flush();
		return contract.getRevenue().getMoney();
	}

	@Transactional
	public long calculateContractRecognition(long contractID) {
		Contract contract = findContract(contractID);
		contract.calculateRecognitions();
		Money money = contract.recognizedRevenue(MfDate.today());
		return money.getMoney();
	}

}
