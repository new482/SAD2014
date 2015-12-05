package com.mid2014.exam.service;

import java.util.Date;

import com.mid2014.exam.domain.ExchangeAnnoucement;
import com.mid2014.exam.domain.Staff;
import com.mid2014.exam.domain.Student;

public interface StaffService {
	public void createExchangeAnnouncement(String programName,
			String programDescription, Date deadline, Staff staff);

	public void createOffer(Student student, Staff staff,
			ExchangeAnnoucement exchangeAnnoucement, String description);

}
