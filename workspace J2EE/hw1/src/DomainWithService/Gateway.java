package DomainWithService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Gateway {
	/* JDBC connection */
	private final String driverName = "com.mysql.jdbc.Driver";
	private final String serverName = "localhost"; 
	private final String portNumber = "3306";
	private final String sid = "hw1";
	private final String url = "jdbc:mysql://" + serverName + ":" + portNumber + "/" + sid;
	private final String dbUsername = "root";
	private final String dbPassword = "root";
	
	
    private Connection db;
    
    Gateway(){
    	
    	try {
		  	Class.forName(driverName);
    	}catch (ClassNotFoundException e) {
		  e.printStackTrace();
    	}
	  
    	try{
    		db = DriverManager.getConnection(url,dbUsername,dbPassword);
    		System.out.println("--------------");
    		System.out.println("JDBC Connected");
    		System.out.println("--------------\n");
    	}catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    }

}
