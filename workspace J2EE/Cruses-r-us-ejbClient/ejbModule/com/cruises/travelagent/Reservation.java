package com.cruises.travelagent;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity @Table(name="Reservation")
public class Reservation implements java.io.Serializable {
	@Id @GeneratedValue
    private int id;
    private float amountPaid;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ManyToOne
    private Cruise cruise;
    @ManyToMany
    private Set<Customer> customers = new HashSet<Customer>();
    @ManyToMany
    private Collection<Cabin> cabins = new ArrayList<Cabin>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(float amountPaid) {
		this.amountPaid = amountPaid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Cruise getCruise() {
		return cruise;
	}
	public void setCruise(Cruise cruise) {
		this.cruise = cruise;
	}
	public Set<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}
	public Collection<Cabin> getCabins() {
		return cabins;
	}
	public void setCabins(Collection<Cabin> cabins) {
		this.cabins = cabins;
	}
    
}
