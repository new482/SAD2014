package com.bazaar.accounts;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

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
    
    public String sayHello(String name)
    {
       return getClass().getName() + " says hello to " + name + ".";
    }

}
