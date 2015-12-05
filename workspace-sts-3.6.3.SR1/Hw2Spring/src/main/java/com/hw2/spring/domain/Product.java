package com.hw2.spring.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;

	@OneToMany
	private Collection<Contract> contractList = new ArrayList<Contract>();

	@Transient
	private RecognitionStrategy recognitionStrategy;
	
	private String name;

	// Maintain the default constructor
	public Product() {
	}

	public Product(String name, RecognitionStrategy recognitionStrategy) {
		this.name = name;
		this.recognitionStrategy = recognitionStrategy;
	}

	public long getID() {
		return this.id;
	}

	public static Product newWordProcessor(String name) {
		return new Product(name, new CompleteRecognitionStrategy());
	}

	public static Product newSpreadsheet(String name) {
		return new Product(name, new ThreeWayRecognitionStrategy(60, 90));
	}

	public static Product newDatabase(String name) {
		return new Product(name, new ThreeWayRecognitionStrategy(30, 60));
	}

	void calculateRevenueRecognitions(Contract contract) {
		recognitionStrategy.calculateRevenueRecognitions(contract);
	}
}
