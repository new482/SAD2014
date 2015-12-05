package com.hw2.spring.service;

import com.hw2.spring.domain.Product;

public interface ProductService {
	public long createNewProcessor(String name);
	public long createNewSpreadSheet(String name);
	public long createNewDatabase(String name);
	public Product findProduct(long id);
}
