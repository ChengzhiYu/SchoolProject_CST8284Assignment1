package cst8284.asgmt1.roomScheduler;

public class RoomBooking {
	private ContactInfo contactInfo;
	private Activity activity;
	private TimeBlock timeblock;
	
	public RoomBooking(TimeBlock timeblock, ContactInfo contactInfo, Activity activity) {
		this.contactInfo = contactInfo;
		this.activity = activity;
		this.timeblock = timeblock;
	}
	
	public ContactInfo getContactInfo() {
		return contactInfo;
	}
	public Activity getActivity() {
		return activity;
	}
	public TimeBlock getTimeblock() {
		return timeblock;
	}
	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
		
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public void setTimeBlock(TimeBlock timeblock) {
		this.timeblock = timeblock;
	}
	public String toString() {
		//return contactInfo.toString() + activity.toString()+ timeblock.toString();
		/*---------------
		8:00 - 14:00
		Event: AGM
		Description: Annual meeting of farmers
		Contact Information: Bob MacDonald
		Phone: 111-222-3333
		Farmer's Market
		---------------
		*/
		
		return "---------------" + "\n" + 
		        timeblock.toString() +  "\n" +
		        activity.toString() +  "\n" +
		        contactInfo.toString() +  "\n" +
		        "---------------";
		        
		        
		         
		      
	}
}
