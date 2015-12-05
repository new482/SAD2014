package com.sad2014.midterm.service;

import java.util.Collection;

import com.sad2014.midterm.domain.*;

public interface VotingService {
	public Collection selectOffice(Office office);
	public void vote(Student student,Nominee nominee, Office office);
}
