package com.mid2014.exam.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Application implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	ExchangeAnnoucement exchangeAnnoucement;
	@ManyToOne
	Student student;
	
	String evaluation;
	int interviewStatus;
	
	@OneToMany(cascade={CascadeType.ALL})
	private Collection <Document> documentSet;
	
	public Application(){
		documentSet = new ArrayList<Document>();
	}
	
	public Application(ExchangeAnnoucement exchangeAnnoucement,Student student){
		documentSet = new ArrayList<Document>();
		this.exchangeAnnoucement = exchangeAnnoucement;
		this.student = student;
		this.interviewStatus = 2;
	}
	
	public void addDocument(Document document){
		documentSet.add(document);
	}
	
	
	public Collection<Document> getDocumentSet() {
		return documentSet;
	}
	
	@OneToMany(cascade={CascadeType.ALL})
	public void setDocumentSet(Collection<Document> documentSet) {
		this.documentSet = documentSet;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public ExchangeAnnoucement getExchangeAnnoucement() {
		return exchangeAnnoucement;
	}

	public void setExchageAnnoucement(ExchangeAnnoucement exchangeAnnoucement) {
		this.exchangeAnnoucement = exchangeAnnoucement;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public int getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(int interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

}
