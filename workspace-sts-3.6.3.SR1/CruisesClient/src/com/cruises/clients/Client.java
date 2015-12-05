package com.cruises.clients;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.cruises.travelagent.Address;
import com.cruises.travelagent.CreditCard;
import com.cruises.travelagent.Customer;
import com.cruises.travelagent.TravelAgentRemote;


public class Client {

	public static void main(String[] args) {
		
		try
	      {
	         Properties properties = new Properties();
	         properties.put(Context.INITIAL_CONTEXT_FACTORY,
	               "org.apache.openejb.client.RemoteInitialContextFactory");
	         properties.put("java.naming.provider.url",
	               "ejbd://localhost:4201");
	         Context context = new InitialContext(properties);
	         TravelAgentRemote travelAgent =
	               (TravelAgentRemote) context
	               .lookup("TravelAgentRemote");
	         
	         Address address = new Address();
	         address.setCity("Bangkok");
	         address.setState("No");
	         address.setStreet("Ladprao");
	         address.setZip("10310");
	         
	         Customer customer1 = new Customer("Matt", "Dailey");
	         CreditCard creditCard1 = new CreditCard("4293858334898769");
	         creditCard1.setCustomer(customer1);
	         customer1.setCreditCard(creditCard1);
	         customer1.setAddress(address);
	         
	         int customerId = travelAgent.createCustomer(customer1);
	         int addressId = travelAgent.addAddress(address);
	         
	         
	         
	      }
	      catch (javax.naming.NamingException ne)
	      {
	         ne.printStackTrace();
	      }	

	}

}
