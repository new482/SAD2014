package Test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import Given.*;
import DomainModel.*;

public class TestDomainModel {

	@Test
	public void calculateRecongnitionTest() {
		Product product = Product.newWordProcessor("TestWordProcessor");
		RevenueRecognition revenueRecognition = new RevenueRecognition();
		Contract contract = new Contract(product, Money.dollars(1), MfDate.today());
		contract.calculateRecognitions();
		Money revenue = contract.recognizedRevenue(MfDate.today());
		
		Assert.assertEquals("Test CalculateRecognition Error", 100, revenue.getMoney());
	}

}
