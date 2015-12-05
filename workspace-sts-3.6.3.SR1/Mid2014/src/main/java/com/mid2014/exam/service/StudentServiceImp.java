package com.mid2014.exam.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mid2014.exam.domain.Application;
import com.mid2014.exam.domain.Document;
import com.mid2014.exam.domain.ExchangeAnnoucement;
import com.mid2014.exam.domain.Student;

@Service
public class StudentServiceImp implements StudentService {
	@PersistenceContext
	private EntityManager entityManager;
	//private Collection<ExchangeAnnoucement> exchangeAnnoucement;
	private ExchangeAnnoucement exchangeAnnoucement;
	private Application application;
	private Student student;
	private Collection<Document> document;
	

	@Transactional
	public ExchangeAnnoucement listExchangeAnnouncement() {
		exchangeAnnoucement = new ExchangeAnnoucement();
		entityManager.persist(exchangeAnnoucement);
		
		return this.exchangeAnnoucement;
	}

	@Transactional
	public long createApplication(ExchangeAnnoucement exchangeAnnoucement,
			Student student) {
		application = new Application(exchangeAnnoucement, student);
		entityManager.persist(application);
		
		return application.getId();

	}

	@Transactional
	public void acceptOffer(Application application, int status) {
		// TODO Auto-generated method stub

	}

}
