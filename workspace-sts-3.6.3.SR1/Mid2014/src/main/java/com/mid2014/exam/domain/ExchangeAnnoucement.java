package com.mid2014.exam.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ExchangeAnnoucement implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	String programName;
	String description;
	Date dateline;
	@ManyToOne
	private Staff staff;
	
	@OneToMany(cascade={CascadeType.ALL})
	Collection<Application> applicationSet;
	
	public ExchangeAnnoucement(){
		
	}
	
	public ExchangeAnnoucement(String programName, String description){
		applicationSet = new ArrayList<Application>();
		this.programName = programName;
		this.description = description;
		//this.dateline = dateline;
	}
	
	public ExchangeAnnoucement(Staff staff, String programName, String description, Date dateline){
		//applicationSet = new ArrayList<Application>();
		this.staff = staff;
		this.programName = programName;
		this.description = description;
		this.dateline = dateline;
	}
	

	public void addApplication(Application application){
		applicationSet.add(application);
	}
	
	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateline() {
		return dateline;
	}

	public void setDateline(Date dateline) {
		this.dateline = dateline;
	}

	public Collection<Application> getApplicationSet() {
		return applicationSet;
	}

	public void setApplicationSet(Collection<Application> applicationSet) {
		this.applicationSet = applicationSet;
	}
}
