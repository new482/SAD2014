package com.hw2.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.hw2.domain.Contract;
import com.hw2.domain.Product;
import com.hw2.domain.RevenueRecognition;
import com.hw2.domain.base.MfDate;
import com.hw2.domain.base.Money;

public class JpaController {

	private EntityManager em;
	private Product product;
	private Contract contract;

	public JpaController() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("Hw2JpaProject");
		if (emf == null) {
			throw new IllegalStateException(
					"EntityManagerFactory is unavailable");
		}

		em = emf.createEntityManager();

		if (em == null) {
			throw new IllegalStateException("EntityManager is unavailable");
		}

		createProduct();
		createContract();
		addContractRevenueRecognition(contract.getID(), 100);
	}

	private void createProduct() {
		product = Product.newWordProcessor("TestProduct");
		em.persist(product);
		em.flush();
		System.out.print(product.getID() + "   " + product.getName());

	}

	public void createContract() {
		contract = new Contract(product, Money.dollars(100), MfDate.today());
		em.persist(contract);
		em.flush();
		System.out.print(contract.getID());
	}

	public void addContractRevenueRecognition(long contractID, double amount) {
		contract = em.find(Contract.class, contractID);
		RevenueRecognition r = new RevenueRecognition(Money.dollars(amount),
				MfDate.today());
		contract.addRevenueRecognition(r);
		em.persist(contract);
		em.persist(r);
		em.flush();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JpaController();
	}

}
