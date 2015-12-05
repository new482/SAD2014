package com.hw2.domain.base;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Embeddable;

public class MfDate extends GregorianCalendar implements Serializable{
	private static final long serialVersionUID = 1L;

	public MfDate(){
		
	}
	
	public MfDate(java.util.Date sqlDateWhenSigned) {
		//this.setTime(date);
		this.setTimeInMillis(sqlDateWhenSigned.getTime());
	}

	public MfDate(long date) {
		this.setTimeInMillis(date);
	}

	public MfDate(GregorianCalendar date) {
		this.setTimeInMillis(date.getTimeInMillis());
	}

	public MfDate(Calendar calendar) {
		this.setTimeInMillis(calendar.getTimeInMillis());
	}

	public Date toSqlDate() {
		return new Date(this.getTimeInMillis());
	}

	public static MfDate today() {
		return new MfDate(GregorianCalendar.getInstance());
	}

	public MfDate addDays(int day) {
		this.add(MfDate.DATE, day);
		return this;
	}

	/*
	public static void main(String args[]) {
		MfDate date = new MfDate(new Date(GregorianCalendar.getInstance()
				.getTimeInMillis()));

		System.out.println(date.toSqlDate());
		date.addDays(5);
		System.out.println(date.toSqlDate());
		date.addDays(6);
		System.out.println(date.toSqlDate());
	}
	*/
}
