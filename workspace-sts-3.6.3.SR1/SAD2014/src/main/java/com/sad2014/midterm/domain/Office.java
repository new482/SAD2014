package com.sad2014.midterm.domain;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Office {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade={CascadeType.ALL})
	private Collection<Nominee> nominee;
	
	private String description;
	
	public void addNominee(Nominee nominee){
		this.nominee.add(nominee);
	}

	public Collection<Nominee> getNominee() {
		return nominee;
	}

	public void setNominee(Collection<Nominee> nominee) {
		this.nominee = nominee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
