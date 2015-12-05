package com.cruises.travelagent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity @Table(name="CreditCard")
public class CreditCard implements java.io.Serializable {
	
	@Id @GeneratedValue
    private int id;
    private String number;
    @OneToOne(mappedBy="creditCard")
    private Customer customer;
    
	public CreditCard(String number) {
		// TODO Auto-generated constructor stub
		this.number = number;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    
    
}
