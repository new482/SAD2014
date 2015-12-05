package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import DomainModel.Contract;
import DomainModel.Product;
import DomainModel.RevenueRecognition;
import Given.MfDate;
import Given.Money;

public class TestDomainModel {

	@Test
	public void calculateRecongnitionTest() {
		Product product = Product.newWordProcessor("TestWordProcessor");
		RevenueRecognition revenueRecognition = new RevenueRecognition();
		Contract contract = new Contract(product, Money.dollars(1), MfDate.today());
		contract.calculateRecognitions();
		Money revenue = contract.recognizedRevenue(MfDate.today());
		
		assertEquals("Test CalculateRecognition Error", 100, revenue.getMoney());
	}

}
