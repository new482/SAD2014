package DomainModel;

import Given.*;

public class RevenueRecognition{

	private Money amount;
	private MfDate date;
	
	public RevenueRecognition(Money amount, MfDate date) {
		this.amount = amount;
		this.date = date;
	}
	
	public RevenueRecognition(){
		//Maintain default constructor
	}
	
	public Money getAmount() {
		return amount;
	}
	
	boolean isRecognizableBy(MfDate asOf) {
		return asOf.after(date) || asOf.equals(date);
	}
}
