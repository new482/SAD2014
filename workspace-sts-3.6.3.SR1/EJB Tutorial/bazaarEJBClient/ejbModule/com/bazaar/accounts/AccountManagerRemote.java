package com.bazaar.accounts;

import java.util.Set;

import javax.ejb.Remote;

@Remote
public interface AccountManagerRemote {
	public String sayHello(String name);
	public long createUser( User user );
	public User findUser( long id );
	public Set<CreditCard> creditCardsForUser(long id);
	public void addCreditCardtoUser(long id, CreditCard creditCard);
}
