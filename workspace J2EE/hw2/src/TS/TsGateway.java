package TS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Given.*;


public class TsGateway {
	
	/* JDBC connection */
	private final String driverName = "com.mysql.jdbc.Driver";
	private final String serverName = "localhost"; 
	private final String portNumber = "3306";
	private final String sid = "hw1";
	private final String url = "jdbc:mysql://" + serverName + ":" + portNumber + "/" + sid;
	private final String dbUsername = "root";
	private final String dbPassword = "root";
	
	
    private Connection db;
    
    public TsGateway(){
    	
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
    
    public ResultSet findContract (long contractID) throws SQLException{
	      PreparedStatement stmt = db.prepareStatement(findContractStatement);
	      stmt.setLong(1, contractID);
	      ResultSet result = stmt.executeQuery();
	      return result;
	}
	
	private static final String findContractStatement =
			"SELECT * FROM contracts c, products p " +
			"WHERE c.id = ? AND c.product = p.id";
	
    public ResultSet findProduct (String productType) throws SQLException{
	      PreparedStatement stmt = db.prepareStatement(findProductStatement);
	      stmt.setString(1, productType);
	      ResultSet result = stmt.executeQuery();
	      return result;
	}
	
	private static final String findProductStatement =
			"SELECT id FROM products p " +
			"WHERE p.type = ?";
	
	
	public ResultSet findRecognitionsFor(long contractID, MfDate asof) throws SQLException {
		PreparedStatement stmt = db.prepareStatement(findRecognitionsStatement);
		stmt.setLong(1, contractID);
		stmt.setDate(2, asof.toSqlDate());
		ResultSet result = stmt.executeQuery();
			  			
		return result;
	}
			   
	private static final String findRecognitionsStatement =
			      "SELECT amount " +
			      "FROM revenueRecognitions " +
			      "WHERE contract = ? AND recognizedOn <= ?";
			   
	
				      
	public void insertRecognition (long contractID, Money amount, MfDate asof) throws SQLException {
		PreparedStatement stmt = db.prepareStatement(insertRecognitionStatement);
		stmt.setLong(1, contractID);
		stmt.setBigDecimal(2, amount.amount());
		stmt.setDate(3, asof.toSqlDate());
		stmt.executeUpdate();
	}
	
	private static final String insertRecognitionStatement = "INSERT INTO revenueRecognitions VALUES (?, ?, ?)";
	
	
	//Insert product
	public int insertProduct(String productName, String productType) throws SQLException{

	     PreparedStatement stmt = db.prepareStatement(insertProductStatement, PreparedStatement.RETURN_GENERATED_KEYS);
	     stmt.setString(1, productName);
	     stmt.setString(2, productType);
	     stmt.executeUpdate();
	     
	     ResultSet resultSet = stmt.getGeneratedKeys();
	     resultSet.next();
	     
	     return resultSet.getInt(1);
	}	
	private static final String insertProductStatement = "INSERT products(name,type) VALUES (?, ?)";
	
	
	//Insert contract
	public int insertContract (long productID, Money amount, MfDate asof) throws SQLException{
		PreparedStatement stmt = db.prepareStatement(insertContractStatement, PreparedStatement.RETURN_GENERATED_KEYS);
		stmt.setLong(1, productID);
		stmt.setBigDecimal(2, amount.amount());
		stmt.setDate(3, asof.toSqlDate());
		stmt.executeUpdate();
		
		ResultSet resultSet = stmt.getGeneratedKeys();
	    resultSet.next();
	     
	    return resultSet.getInt(1);
	}
	private static final String insertContractStatement = "INSERT INTO contracts(product,revenue,dateSigned) VALUES (?, ?, ?)";
	
	
}
