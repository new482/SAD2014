package Test;
import static org.junit.Assert.*;

import java.sql.SQLException;

import junit.framework.Assert;

import org.junit.Test;

import Given.*;
import TS.TsGateway;
import TS.TsRecognitionService;


public class TestTS {
	
	TsRecognitionService reg = new TsRecognitionService();
	private static long productID;
	private static long contractID;
	
	//@Test
	public void testAddProduct() throws SQLException{
		productID = reg.addProduct("TestProduct", "W");
		Assert.assertNotNull(productID);
	}
	
	//@Test
	public void testAddContract() throws SQLException{
		contractID = reg.addContract(productID, 7000);
		Assert.assertNotNull(contractID);
	}
	
	@Test
	public void testCalculateRevenueRecognitions() throws ApplicationException {
		Assert.assertTrue(reg.calculateRevenueRecognitions(16));
	}
	
}
