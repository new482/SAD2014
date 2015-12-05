package com.mid2014.exam.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Student implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	
	//private Application application;
	
	
	@OneToMany(cascade={CascadeType.ALL})
	private Collection <Offer> offerSet;
	
	@OneToMany(cascade={CascadeType.ALL})
	private Collection <Application> applicationSet;
	
	public Student(){
		
	}
	
	public Student(String firstname, String lastname, String email, String password){
		this.firstname=firstname;
		this.lastname=lastname;
		this.email=email;
		this.password=password;
	}
	
	public void addApplication(Application application){
		applicationSet.add(application);
	}
	
	public void setFirstname(String firstname) {
		// TODO Auto-generated method stub
		this.firstname = firstname;
	}

	public String getFirstname() {
		// TODO Auto-generated method stub
		return this.firstname;
	}

	public long getID() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public void setLastname(String lastname) {
		// TODO Auto-generated method stub
		this.lastname = lastname;
	}

	public String getLastname() {
		// TODO Auto-generated method stub
		return this.lastname;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Collection<Offer> getOfferSet() {
		return offerSet;
	}

	public void setOfferSet(Collection<Offer> offerSet) {
		this.offerSet = offerSet;
	}

	public Collection<Application> getApplicationSet() {
		return applicationSet;
	}

	public void setApplicationSet(Collection<Application> applicationSet) {
		this.applicationSet = applicationSet;
	}
	

}
