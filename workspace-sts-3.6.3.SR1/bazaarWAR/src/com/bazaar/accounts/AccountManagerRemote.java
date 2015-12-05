package com.bazaar.accounts;

import javax.ejb.Remote;

@Remote
public interface AccountManagerRemote {
	public String sayHello(String name);
}
