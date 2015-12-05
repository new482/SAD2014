package TS;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import Given.ApplicationException;

public class Controller {
	
	public static void main(String[] args) throws ApplicationException, SQLException {
		// TODO Auto-generated method stub
		TsRecognitionService ts = new TsRecognitionService();
		
		long input = Long.parseLong(JOptionPane.showInputDialog("Please enter a Contract Number"));
		System.out.print(ts.calculateRevenueRecognitions(input));
		
	}

}
