package TS;
import java.sql.*;

import Given.ApplicationException;
import Given.MfDate;
import Given.Money;

public class TsRecognitionService {
	
	private TsGateway db;
	
	public TsRecognitionService(){
		db = new TsGateway();
	}
	
	public Money recognizedRevenue(long contractNumber, MfDate asOf) throws ApplicationException {
		Money result = Money.dollars(0);
				      
		try {
				ResultSet rs = db.findRecognitionsFor(contractNumber, asOf);
				while (rs.next()) {
					result = result.add(Money.dollars(rs.getBigDecimal("amount")));
				}
				         
				return result;
				      
		}catch (SQLException e) {			
			throw new ApplicationException (e);
		}
	}
	
	public boolean calculateRevenueRecognitions(long contractNumber) throws ApplicationException {
		try {
				ResultSet contracts = db.findContract(contractNumber);
				contracts.next();
				Money totalRevenue = Money.dollars(contracts.getBigDecimal("c.revenue"));
				MfDate recognitionDate = new MfDate(contracts.getDate("c.dateSigned"));
				String type = contracts.getString("p.type");
				if (type.equals("S")){
					Money[] allocation = totalRevenue.allocate(3);
					db.insertRecognition(contractNumber, allocation[0], recognitionDate);
					db.insertRecognition(contractNumber, allocation[1], recognitionDate.addDays(60));
					db.insertRecognition(contractNumber, allocation[2], recognitionDate.addDays(90));
				} else if (type.equals("W")){
							db.insertRecognition(contractNumber, totalRevenue, recognitionDate);
						} else if (type.equals("D")) {
							Money[] allocation = totalRevenue.allocate(3);
							db.insertRecognition(contractNumber, allocation[0], recognitionDate);
							db.insertRecognition(contractNumber, allocation[1], recognitionDate.addDays(30));
							db.insertRecognition(contractNumber, allocation[2], recognitionDate.addDays(60));
						}
				/*
				contracts.close();
				contracts = db.findContract(contractNumber);
				contracts.next();
				String info = "Contract Number: "+contracts.getString("c.id")+"\n"+
						"Product Name: "+contracts.getString("p.name")+"\n"+
						"Amount: "+contracts.getString("c.revenue")+"\n"+
						"Date: "+contracts.getString("c.dateSigned");
				*/
				
				return true;		
				
		} catch (SQLException e) {
			throw new ApplicationException (e);
		}
	}	
	
	public int addContract(long productID, long amount) throws SQLException{		
		return db.insertContract(productID, Money.dollars(amount), MfDate.today());	
	}
	
	public int addProduct(String productName, String productType) throws SQLException{
		return db.insertProduct(productName, productType);		
	}
	
	
	
	/*
	public String[] findContractDetail(long contractNumber) throws SQLException{
		String[] contractDetail = new String[4];
		int i=0;
		ResultSet rs = db.findContract(contractNumber);
		while (rs.next()){
			contractDetail[i++] = rs.getString("p.name");
			//i++;
		}
		return contractDetail;
	}
	
	
	public Long findNewProductID(String productName, String productType) throws ApplicationException, SQLException{
		
		ResultSet product = db.insertProduct(productName, productType);
				
	     if (product.next()){
	    	 
	        return product.getLong(1);
	     } else {
	         throw new ApplicationException("Cannot get product id");
	     }		
	}
    */
    
}
