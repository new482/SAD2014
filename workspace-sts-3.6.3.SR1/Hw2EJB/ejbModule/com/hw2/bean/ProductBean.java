package com.hw2.bean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hw2.bean.ProductBeanRemote;
import com.hw2.domain.Product;

/**
 * Session Bean implementation class ProductBean
 */
@Stateless
@LocalBean
public class ProductBean implements ProductBeanRemote {

	public ProductBean() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext
	private EntityManager em;

	// @Transactional
	public long createNewProcessor(String name) {
		Product product = Product.newWordProcessor(name);
		em.persist(product);
		em.flush();
		return product.getID();
	}

	// @Transactional
	public long createNewSpreadSheet(String name) {
		Product product = Product.newSpreadsheet(name);
		em.persist(product);
		em.flush();
		return product.getID();
	}

	// @Transactional
	public long createNewDatabase(String name) {
		Product product = Product.newDatabase(name);
		em.persist(product);
		em.flush();
		return product.getID();
	}

	// @Transactional
	public Product findProduct(long id) {
		return em.find(Product.class, id);
	}

	@Override
	public String findProductName(long id) {
		Query query = em.createQuery(
				"SELECT p FROM Product p WHERE p.id = :productId")
				.setParameter("productId", id);
		Product product = (Product) query.getSingleResult();
		return product.getName();
	}

}
