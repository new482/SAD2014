package com.mid2014.exam.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Staff implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	
	private ExchangeAnnoucement exchangeAnnoucement;
	
	@OneToMany
	private Collection <ExchangeAnnoucement> announcementSet;
	
	@OneToMany
	private Collection <Offer> offerSet;

	public void addExchangeAnnoucement(ExchangeAnnoucement exchageAnnoucement){
		announcementSet.add(exchangeAnnoucement);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<ExchangeAnnoucement> getAnnouncementSet() {
		return announcementSet;
	}

	public void setAnnouncementSet(Collection<ExchangeAnnoucement> announcementSet) {
		this.announcementSet = announcementSet;
	}

	public Collection<Offer> getOfferSet() {
		return offerSet;
	}

	public void setOfferSet(Collection<Offer> offerSet) {
		this.offerSet = offerSet;
	}
	
	
}
