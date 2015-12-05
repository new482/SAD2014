package DomainWithService;

import java.util.ArrayList;
//import java.util.*;

import Given.*;

class Contract{
	private Product product;
	private Money revenue;
	private MfDate whenSigned;
	private long contractId;
	private ArrayList<RevenueRecognition> revenueRecognitions = new ArrayList<RevenueRecognition>();
	
	public Contract(Product product, Money revenue, MfDate whenSigned) {
	      this.product = product;
	      this.revenue = revenue;
	      this.whenSigned = whenSigned;
	}

	public void calculateRecognitions() {
		product.calculateRevenueRecognitions(this);
	}
	
	public void addRevenueRecognition(RevenueRecognition revenueRecognition){
		revenueRecognitions.add(revenueRecognition);
	}
	
	public Money getRevenue(){
		return this.revenue;
	}
	
	public MfDate getWhenSigned(){
		return this.whenSigned;
	}
	
	public Money recognizedRevenue(MfDate asOf) {
	      Money result = Money.dollars(0);
	      for ( RevenueRecognition r : revenueRecognitions )
	         if (r.isRecognizableBy(asOf))
	            result = result.add(r.getAmount());
	      return result;
	}
}
