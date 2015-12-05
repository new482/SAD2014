package com.bazaar.web.AccountManagerServlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bazaar.accounts.AccountManagerRemote;

/**
 * Servlet implementation class AccountManagerServlet
 */
//@WebServlet("/AccountManagerServlet")
public class AccountManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @EJB
    AccountManagerRemote accountManager;

    protected void doGet(HttpServletRequest request,
          HttpServletResponse response) throws ServletException, IOException {
       String name = request.getParameter("name");
       if (name == null || name.length() == 0) {
          name = "anonymous";
       }
       response.getWriter().write(accountManager.sayHello(name));
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
