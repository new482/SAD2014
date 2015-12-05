package com.sad2014.midterm.service;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.sad2014.midterm.domain.Nominee;
import com.sad2014.midterm.domain.Office;
import com.sad2014.midterm.domain.Student;

@Service
public class VotingSystemImp implements VotingService {

	@PersistenceContext
	private EntityManager entityManager;

	public void vote(Student student, Nominee nominee, Office office) {
		entityManager.persist(student);
		entityManager.persist(nominee);
		entityManager.persist(office);
		
	}

	public Collection selectOffice(Office office) {
		entityManager.persist(office);
		
		return office.getNominee();
	}

}
