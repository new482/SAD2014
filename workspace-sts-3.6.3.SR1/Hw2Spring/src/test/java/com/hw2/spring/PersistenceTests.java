package com.hw2.spring;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hw2.spring.service.ContractService;
//import com.hw2.spring.domain.Product;
import com.hw2.spring.service.ProductService;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class PersistenceTests {

	//@PersistenceContext
	//private EntityManager em;

	@Autowired
	private ProductService productService;

	@Autowired
	private ContractService contractService;

	@Test
	@Transactional
	public void testCreateProduct() {
		long productID = productService.createNewProcessor("testProduct");
		assertNotNull(productService.findProduct(productID));
	}
	
	@Test
	@Transactional
	public void testCreateContract(){
		long productID = productService.createNewProcessor("testContract");
		assertNotNull(contractService.createContract(productService.findProduct(productID)));
	}
}
