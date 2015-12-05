package com.hw2.bean;

import javax.ejb.Remote;

import com.hw2.domain.Product;

@Remote
public interface ProductBeanRemote {
	public long createNewProcessor(String name);

	public long createNewSpreadSheet(String name);

	public long createNewDatabase(String name);

	public Product findProduct(long id);
	
	public String findProductName(long id);
}
