package com.bazaar.web;


import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bazaar.accounts.AccountManagerRemote;
import com.bazaar.accounts.User;

/**
 * Servlet implementation class AccountManagerServlet
 */
//@WebServlet("/AccountManagerServlet")
public class AccountManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @EJB
    AccountManagerRemote accountManager;
	   
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String requestUri = request.getRequestURI();
    	  if (requestUri != null && requestUri.endsWith("/sayHello"))
    	  {
    	     String name = request.getParameter("name");
    	     if (name == null || name.length() == 0)
    	     {
    	        name = "anonymous";
    	     }
    	     response.getWriter().write(accountManager.sayHello(name));
    	  } else if (requestUri != null && requestUri.endsWith("/createUser"))
    	  {
    	     String firstName = request.getParameter("firstName");
    	     String lastName = request.getParameter("lastName");
    	     String username = request.getParameter("username");
    	     String password = request.getParameter("password");
    	     User user = new User(firstName, lastName, username, password);
    	     accountManager.createUser(user);
    	     response.getWriter().write("Created user successfully.");
    	  } else if (requestUri != null && requestUri.endsWith("/showUser"))
    	  {
    	     User user = null;
    	     String id = request.getParameter("id");
    	     if (id != null)
    	        user = accountManager.findUser(Long.parseLong(id));
    	     request.setAttribute("user", user);
    	     RequestDispatcher dispatcher = request
    	           .getRequestDispatcher("user.jsp");
    	     dispatcher.forward(request, response);
    	  }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	
	

}
