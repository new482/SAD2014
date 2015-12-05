package com.mid2014.exam.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Offer implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	private String description;
	private int status;
	private Staff staff;
	private Student student;
	private ExchangeAnnoucement exchangeAnnoucement;
	
	
	public ExchangeAnnoucement getExchangeAnnoucement() {
		return exchangeAnnoucement;
	}
	public void setExchangeAnnoucement(ExchangeAnnoucement exchangeAnnoucement) {
		this.exchangeAnnoucement = exchangeAnnoucement;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
}
