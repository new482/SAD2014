package com.titan.travel.service;

import com.titan.travel.domain.Cabin;

public interface TravelAgentService {
	public long createCabin(Cabin cabin);
	public Cabin findCabin(long id);
}
