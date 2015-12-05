package com.hw2.bean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hw2.bean.ContractBeanRemote;
import com.hw2.domain.Contract;
import com.hw2.domain.Product;
import com.hw2.domain.RevenueRecognition;
import com.hw2.domain.base.MfDate;
import com.hw2.domain.base.Money;

/**
 * Session Bean implementation class ContractBean
 */
@Stateless
@LocalBean
public class ContractBean implements ContractBeanRemote {

	public ContractBean() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext
	private EntityManager em;

	// @Transactional
	public Contract findContract(long id) {
		return em.find(Contract.class, id);
	}

	// @Transactional
	public long createContract(Product product) {
		Contract contract = new Contract(product, Money.dollars(100),
				MfDate.today());
		em.persist(contract);
		em.flush();
		return contract.getID();
	}

	// @Transactional
	public long addContractRevenueRecognition(long contractID, double amount) {
		Contract contract = findContract(contractID);
		RevenueRecognition r = new RevenueRecognition(Money.dollars(amount), MfDate.today());
		contract.addRevenueRecognition(r);
		em.persist(contract);
		em.persist(r);
		em.flush();
		
		return contract.getRevenue().getMoney();
	}

	// @Transactional
	public long calculateContractRecognition(long contractID) {
		Contract contract = findContract(contractID);
		contract.calculateRecognitions();
		Money money = contract.recognizedRevenue(MfDate.today());
		return money.getMoney();
	}
	
	public long getContractRevenue(long contractID){
		Contract contract = findContract(contractID);
		return contract.getRevenue().getMoney();
	}

}
