package com.hw2.domain;

import java.io.Serializable;
//import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.hw2.domain.base.MfDate;
import com.hw2.domain.base.Money;

@Entity
@Table(name = "Contract")
public class Contract implements Serializable {

	private static final long serialVersionUID = 1L;
	// private static final Logger logger = Logger.getLogger("Contract");

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	@Transient
	private Product product;

	@OneToMany(cascade = { CascadeType.ALL })
	private Collection<RevenueRecognition> revenueRecognitions = new ArrayList<RevenueRecognition>();

	private Money revenue;

	@Transient
	private MfDate whenSigned;

	@Temporal(TemporalType.DATE)
	private Date sqlDateWhenSigned;

	// To maintain the default constructor
	public Contract() {
	}

	public Contract(Product product, Money revenue, MfDate whenSigned) {
		this.product = product;
		this.revenue = revenue;
		this.whenSigned = whenSigned;
		this.sqlDateWhenSigned = this.whenSigned.toSqlDate();
	}

	public long getID() {
		return this.id;
	}

	public Contract getContract() {
		return this;
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

	@PostLoad
	public void setMfDateFromSqlDate() {
		this.whenSigned = new MfDate(this.sqlDateWhenSigned);
	}

	public Money recognizedRevenue(MfDate asOf) {
		Money result = Money.dollars(0);
		for (RevenueRecognition r : revenueRecognitions)
			if (r.isRecognizableBy(asOf))
				result = result.add(r.getAmount());
		return result;
	}
}
