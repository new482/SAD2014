package com.cruises.travelagent;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity @Table(name="Customer")
public class Customer implements java.io.Serializable{

	@Id @GeneratedValue
    private int id;
    private String lastName;
    private String firstName;
    private boolean hasGoodCredit;
    @OneToMany(cascade={CascadeType.ALL})
    private Collection<Phone> phoneNumbers = new ArrayList<Phone>();
    @OneToOne(cascade={CascadeType.ALL})
    private CreditCard creditCard;
    @OneToOne(cascade={CascadeType.ALL})
    private Address address;
    @ManyToMany
    private Collection<Reservation> reservations = new ArrayList<Reservation>();
	
    public Customer(String firstName, String lastName) {
		// TODO Auto-generated constructor stub
    	this.firstName = firstName;
    	this.lastName = lastName;
	}
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public boolean isHasGoodCredit() {
		return hasGoodCredit;
	}
	public void setHasGoodCredit(boolean hasGoodCredit) {
		this.hasGoodCredit = hasGoodCredit;
	}
	public Collection<Phone> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(Collection<Phone> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Collection<Reservation> getReservations() {
		return reservations;
	}
	public void setReservations(Collection<Reservation> reservations) {
		this.reservations = reservations;
	}
    
    
}
