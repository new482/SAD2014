package com.bazaar.accounts;

import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class AccountManager
 */
@Stateless
@LocalBean
public class AccountManager implements AccountManagerRemote {

    /**
     * Default constructor. 
     */
    public AccountManager() {
        // TODO Auto-generated constructor stub
    }
    public String sayHello(String name){
       return getClass().getName() + " says hello to " + name + ".";
    }
    
	@Override
	public Set<CreditCard> creditCardsForUser(long id) {
		// TODO Auto-generated method stub
		return null;
	}
    
    @PersistenceContext
	private EntityManager entityManager;

	public long createUser(User user)
	{
		entityManager.persist(user);
		
	   return user.getUserId();
	}
	
	public User findUser(long id)
	{
	   return entityManager.find(User.class, id);
	}

	
	@Override
	public void addCreditCardtoUser(long id, CreditCard creditCard) {
		// TODO Auto-generated method stub
		findUser(id).addCreditCard(creditCard);
	}
}
