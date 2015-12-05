package Test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import DomainWithService.Product;
import DomainWithService.Service;
import Given.MfDate;
import Given.Money;

public class TestDomainWithService {

	//@Test
	public void createProductTest() {
		Service service = new Service();
		Assert.assertEquals(1,service.createProduct("Word", "WordProcessor"));		
	}
	
	//@Test
	public void createContractTest(){
		Service service = new Service();
		Assert.assertEquals(1, service.createContract(Product.newWordProcessor("testWordProcessor"), Money.dollars(1), MfDate.today()));
	}
	
	@Test
	public void calculateContractRecognitionTest(){
		Service service = new Service();
		service.createProduct("Word", "WordProcessor");
		
		int contractNumber = service.createContract(Product.newWordProcessor("testWordProcessor"), Money.dollars(1), MfDate.today());
		
		Assert.assertEquals(100, service.caculateContractRecognition(contractNumber));
		
	}

}
