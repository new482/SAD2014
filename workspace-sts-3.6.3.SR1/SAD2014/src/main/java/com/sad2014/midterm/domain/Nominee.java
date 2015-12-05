package com.sad2014.midterm.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Nominee extends Student{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Collection<Vote> vote;
	
	@OneToMany
	private Office office;
	
	
	public String getStudentID(){
		return super.getStudentID();
	}
	
	public String getStudentName(){
		return super.getName();
	}
	
	public String getStudentEmail(){
		return super.getEmail();
	}

	public Collection<Vote> getVote() {
		return vote;
	}

	public void setVote(Collection<Vote> vote) {
		this.vote = vote;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
}
