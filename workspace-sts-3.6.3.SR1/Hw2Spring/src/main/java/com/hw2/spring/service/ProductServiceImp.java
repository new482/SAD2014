package com.hw2.spring.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hw2.spring.domain.Product;

@Service
public class ProductServiceImp implements ProductService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public long createNewProcessor(String name) {
		Product product = Product.newWordProcessor(name);
		em.persist(product);
		em.flush();
		return product.getID();
	}

	@Transactional
	public long createNewSpreadSheet(String name) {
		Product product = Product.newSpreadsheet(name);
		em.persist(product);
		em.flush();
		return product.getID();
	}

	@Transactional
	public long createNewDatabase(String name) {
		Product product = Product.newDatabase(name);
		em.persist(product);
		em.flush();
		return product.getID();
	}

	@Transactional
	public Product findProduct(long id) {
		return em.find(Product.class, id);
	}

}
