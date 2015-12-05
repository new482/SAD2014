package com.mid2014.exam.service;

import com.mid2014.exam.domain.Application;
import com.mid2014.exam.domain.ExchangeAnnoucement;
import com.mid2014.exam.domain.Student;

public interface StudentService {
	public ExchangeAnnoucement listExchangeAnnouncement();

	public long createApplication(ExchangeAnnoucement exchangeAnnoucement,
			Student student);
	
	public void acceptOffer(Application application, int status);
}
