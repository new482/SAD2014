package com.sad2014.midterm.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany
	private Staff staff;

	private Date nominationPeriod;
	private Date electionPeriod;
	private String officeDescription;

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Date getNominationPeriod() {
		return nominationPeriod;
	}

	public void setNominationPeriod(Date nominationPeriod) {
		this.nominationPeriod = nominationPeriod;
	}

	public Date getElectionPeriod() {
		return electionPeriod;
	}

	public void setElectionPeriod(Date electionPeriod) {
		this.electionPeriod = electionPeriod;
	}

	public String getOfficeDescription() {
		return officeDescription;
	}

	public void setOfficeDescription(String officeDescription) {
		this.officeDescription = officeDescription;
	}

}
