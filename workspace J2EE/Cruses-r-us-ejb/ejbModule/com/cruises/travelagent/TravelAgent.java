package com.cruises.travelagent;

import java.util.Collection;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class TravelAgent
 */
@Stateless
@LocalBean
public class TravelAgent implements TravelAgentRemote {

	@PersistenceContext
	EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
    public TravelAgent() {
        // TODO Auto-generated constructor stub
    }
    
    public Collection<Customer> getCustomers()
    {
      Query query = entityManager.createQuery("SELECT c FROM Customer AS c");
     @SuppressWarnings("unchecked")
        List<Customer> customers = query.getResultList();
        return customers;
    }
    
	@Override
	public int createCustomer(Customer customer1) {
		// TODO Auto-generated method stub		
		entityManager.persist(customer1);
		return customer1.getId();
	}

	@Override
	public int addAddress(Address address) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
