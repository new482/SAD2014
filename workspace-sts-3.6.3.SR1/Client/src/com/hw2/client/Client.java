package com.hw2.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.hw2.bean.ContractBeanRemote;
import com.hw2.bean.ProductBeanRemote;

public class Client {

	static ProductBeanRemote productBean;
	static ContractBeanRemote contractBean;
	long productId;
	long contractId;

	public Client() {
		testCreateProduct();
		testCreateContract();
	}

	// Testing the whole EJB flow
	public void testCreateProduct() {
		productId = productBean.createNewDatabase("TestDatabase");
		System.out.println("Product ID: " + productId);
		System.out.println("Product Name: "
				+ productBean.findProductName(productId));
	}

	// Testing the whole EJB flow
	public void testCreateContract() {
		contractId = contractBean.createContract(productBean
				.findProduct(productId));
		System.out.println("Contract ID: " + contractId);
		System.out.println(contractBean.addContractRevenueRecognition(
				contractId, 100));
	}

	public static void main(String[] args) {
		try {
			Properties properties = new Properties();
			properties.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.apache.openejb.client.RemoteInitialContextFactory");
			properties.put("java.naming.provider.url", "ejbd://localhost:4201");
			Context context = new InitialContext(properties);

			productBean = (ProductBeanRemote) context
					.lookup("ProductBeanRemote");
			contractBean = (ContractBeanRemote) context
					.lookup("ContractBeanRemote");

			new Client();

		} catch (javax.naming.NamingException ne) {
			ne.printStackTrace();
		}
	}

}
