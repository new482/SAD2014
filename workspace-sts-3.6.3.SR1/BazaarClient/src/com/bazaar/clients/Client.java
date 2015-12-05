package com.bazaar.clients;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.bazaar.accounts.AccountManagerRemote;
import com.bazaar.accounts.CreditCard;
import com.bazaar.accounts.User;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
	      {
	         Properties properties = new Properties();
	         properties.put(Context.INITIAL_CONTEXT_FACTORY,
	               "org.apache.openejb.client.RemoteInitialContextFactory");
	         properties.put("java.naming.provider.url",
	               "ejbd://localhost:4201");
	         Context context = new InitialContext(properties);
	         AccountManagerRemote accountManager =
	               (AccountManagerRemote) context
	               .lookup("AccountManagerRemote");

	         //Interact with User.java
	         User user = new User("st116391", "Leerunyakul", "new482", "1234");
	         long newId = accountManager.createUser(user);

	         user = accountManager.findUser(newId);
	         System.out.println("User name: " + user.getFirstName() + " "
	               + user.getLastName() + " username: " + user.getUsername());
	         
	         
	         
	         //Interact with CreditCard.java
	         CreditCard creditCard = new CreditCard();
	         creditCard.setAccountNumber("1234567");
	         creditCard.setNameOnCard("st116391_Card");
	         accountManager.addCreditCardtoUser(newId, creditCard);
	         
	         System.out.println("Accout No. ="+creditCard.getAccoutNumber()+"\n"+
	        		 "Name on card = "+creditCard.getNameOnCard());
	         
	      }
	      catch (javax.naming.NamingException ne)
	      {
	         ne.printStackTrace();
	      }
	}

}
