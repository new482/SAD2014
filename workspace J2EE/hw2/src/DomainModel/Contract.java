package DomainModel;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import Given.MfDate;
import Given.Money;

@Entity
@Table(name = "Contract")
public class Contract implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Product product;

	@OneToMany(cascade = { CascadeType.ALL })
	private ArrayList<RevenueRecognition> revenueRecognitions = new ArrayList<RevenueRecognition>();

	private Money revenue;
	private MfDate whenSigned;

	// To maintain the default constructor
	public Contract() {

	}

	public Contract(Product product, Money revenue, MfDate whenSigned) {
		this.product = product;
		this.revenue = revenue;
		this.whenSigned = whenSigned;
	}

	public void calculateRecognitions() {
		product.calculateRevenueRecognitions(this);
	}

	public void addRevenueRecognition(RevenueRecognition revenueRecognition) {
		revenueRecognitions.add(revenueRecognition);
	}

	public Money getRevenue() {
		return this.revenue;
	}

	public MfDate getWhenSigned() {
		return this.whenSigned;
	}

	public Money recognizedRevenue(MfDate asOf) {
		Money result = Money.dollars(0);
		for (RevenueRecognition r : revenueRecognitions)
			if (r.isRecognizableBy(asOf))
				result = result.add(r.getAmount());
		return result;
	}
}
