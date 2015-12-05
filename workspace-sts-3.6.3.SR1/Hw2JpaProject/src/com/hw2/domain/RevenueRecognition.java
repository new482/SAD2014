package com.hw2.domain;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hw2.domain.base.MfDate;
import com.hw2.domain.base.Money;

@Entity
@Table(name = "RevenueRecognition")
public class RevenueRecognition implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Contract contract;

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
