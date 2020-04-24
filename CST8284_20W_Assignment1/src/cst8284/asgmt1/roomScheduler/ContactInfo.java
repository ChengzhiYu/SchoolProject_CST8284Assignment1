package cst8284.asgmt1.roomScheduler;

public class ContactInfo {
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String organization ;
	
	
	public ContactInfo(String firstName, String lastName, String phoneNumber) {	 
		this(firstName,lastName,phoneNumber,"Algonquin College");
				
	}
	
	public ContactInfo(String firstName, String lastName, String phoneNumber, String organization) {
	  this.firstName = firstName;
	  this.lastName = lastName;
	  this.phoneNumber = phoneNumber;
	  this.organization = organization;
	}
	
	
	
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getOrganization() {
		return organization;
	}
	public void setFirstName(String firstName) {
		this.firstName =  firstName;
	}
	public void setLastName(String lastName) {
		this.lastName =  lastName;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber =  phoneNumber;
	}
	public void setOrganization(String organization) {
		this.organization =  organization;
	}
	
	public String toString() {
		
		if (organization.isEmpty()){
		return "Contact Information: "+ firstName + " " + lastName + "\n" + "Phone: " + phoneNumber;
		}
		else{
			return "Contact Information: "+ firstName + " " + lastName + "\n" + "Phone: " + 
		            phoneNumber + "\n" + "Organization: " + organization;
		}
		
	}
	

}
