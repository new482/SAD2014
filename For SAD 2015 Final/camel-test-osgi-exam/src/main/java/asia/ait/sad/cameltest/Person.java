package asia.ait.sad.cameltest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {
   private int customerNumber;
   private String firstName;
   private String lastName;
   private String street;
   private String city;
   private String province;
   private String postalCode;
   private String phone;
public int getCustomerNumber() {
	return customerNumber;
}
public void setCustomerNumber(int customerNumber) {
	this.customerNumber = customerNumber;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getStreet() {
	return street;
}
public void setStreet(String street) {
	this.street = street;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getProvince() {
	return province;
}
public void setProvince(String province) {
	this.province = province;
}
public String getPostalCode() {
	return postalCode;
}
public void setPostalCode(String postalCode) {
	this.postalCode = postalCode;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
   
   
}
