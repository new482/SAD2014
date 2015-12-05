package com.sad2014.midterm.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Vote {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToMany
	private Student student;
	
	@OneToMany
	private Nominee nominee;
	
	@ManyToOne
	private Collection<Office> office;
	
	public Vote(){
		
	}
	
	public Vote(Student student, Nominee nominee, Office office){
		this.student=student;
		this.office.add(office);
		this.nominee=nominee;
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Collection<Office> getOffice() {
		return office;
	}

	public void setOffice(Collection<Office> office) {
		this.office = office;
	}

	public Nominee getNominee() {
		return nominee;
	}

	public void setNominee(Nominee nominee) {
		this.nominee = nominee;
	}
	
}
