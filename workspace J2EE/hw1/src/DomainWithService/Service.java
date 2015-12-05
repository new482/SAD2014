package DomainWithService;


import java.util.Hashtable;

import Given.*;

public class Service {
	
	private static Hashtable<Integer, Product> productTable = new Hashtable<Integer, Product>();
	private static Hashtable<Integer, Contract> contractTable = new Hashtable<Integer, Contract>();
	private int contractIndex=1;
	private int productIndex=1;
	
	public int createProduct(String productName,String productType){
		if(productType.equals("WordProcessor")){
			productTable.put(productIndex++, Product.newWordProcessor(productName));
		}else if(productType.equals("Spreadsheet")){
			productTable.put(productIndex++, Product.newSpreadsheet(productName));
		}else if(productType.equals("Database")){
			productTable.put(productIndex++, Product.newDatabase(productName));
		}
		
		return --productIndex;
	}
	
	public int createContract(Product product, Money revenue, MfDate whenSigned){
		contractTable.put(contractIndex++, new Contract(product, revenue, whenSigned));
		return --contractIndex;
	}
	
	public long caculateContractRecognition(int index){
		if(contractTable.get(index) != null){
			contractTable.get(index).calculateRecognitions();
			Money revenue = contractTable.get(index).recognizedRevenue(MfDate.today());
			return revenue.getMoney();
		}
		return 0;
	}
	
}
