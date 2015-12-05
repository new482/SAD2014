package com.cruises.travelagent;

import java.util.Collection;

import javax.ejb.Remote;

@Remote
public interface TravelAgentRemote {

	int createCustomer(Customer customer1);
	int addAddress(Address address);
	Collection<Customer> getCustomers();

}
