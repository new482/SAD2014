package com.hw2.domain.base;


//@Embeddable
public class ApplicationException extends Exception {

	public ApplicationException(Exception e){
		super(e);
	}

	public ApplicationException(String string) {
		// TODO Auto-generated constructor stub
	}
}
