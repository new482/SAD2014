package DomainModel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import Given.MfDate;
import Given.Money;

@Entity
@Table(name = "RevenueRecognition")
public class RevenueRecognition implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;

	private Money amount;
	private MfDate date;

	public RevenueRecognition(Money amount, MfDate date) {
		this.amount = amount;
		this.date = date;
	}

	// Maintain default constructor
	public RevenueRecognition() {
		
	}

	public Money getAmount() {
		return amount;
	}

	boolean isRecognizableBy(MfDate asOf) {
		return asOf.after(date) || asOf.equals(date);
	}
}
