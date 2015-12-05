package com.bazaar.accounts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
//@Table(name = "CREDITCARD")
public class CreditCard implements java.io.Serializable
{
   static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue
   private Long creditCardId;
   private String nameOnCard;
   private String accountNumber;
   @ManyToOne
   private User user;
   
   public void setAccountNumber(String string) {
	// TODO Auto-generated method stub
	   this.accountNumber = string;
   }

   public void setNameOnCard(String string) {
	// TODO Auto-generated method stub
	   this.nameOnCard = string;
   }
   
   public String getAccoutNumber(){
	   return this.accountNumber;
   }
   
   public String getNameOnCard(){
	   return this.nameOnCard;
   }

}
