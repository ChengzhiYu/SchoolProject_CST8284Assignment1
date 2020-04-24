package cst8284.asgmt1.roomScheduler;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
public class RoomScheduler {
	
	private static Scanner scan = new Scanner(System.in);
	private RoomBooking[] roomBookings = new RoomBooking[80];
	private int lastBookingIndex = -1;
	private final static int ENTER_ROOM_BOOKING = 1;
	private final static int DISPLAY_BOOKING = 2;
	private final static int DISPLAY_DAY_BOOKINGS = 3;
	private final static int EXIT = 0;
	
	public RoomScheduler() {
		lastBookingIndex = -1;
	}
	
	public void launch() {
		int choice = -1;
		do {
			choice =displayMenu();
			if (choice == -1){
				continue;
			}
			executeMenuItem(choice);
		}
		while (choice != 0);
		
	
	}
	
	private int displayMenu() {
		int intAnswer;
		String answer = getResponseTo("Enter a selection from the following menu:\n" + 
							"1. Enter a room booking\n" + 
							"2. Display a booking\n" +
							"3. Display room bookings for the whole day\n" + 
							"0. Exit program\n");
		
		try{
		intAnswer = Integer.parseInt(answer);
		}
		catch (Exception e){
			System.out.println("please enter a valid integer");
			intAnswer =-1;
		}
		
		return intAnswer;
	}
	
	private void executeMenuItem(int choice) {
		boolean saved = false;
		RoomBooking booking = null;
		Calendar cal = null;
		
		switch(choice) {
		
		case 1:
			
			booking= makeBookingFromUserInput();
			saved = saveRoomeBooking(booking);
			if(saved==true){

	    		System.out.println("Booking time and date saved.\n");
		    }
		    else{
		    	System.out.println("Can't save this booking , it is confliction with an existing booking.\n");
		    }
			
			break;

		case 2:
			 cal = makeCalendarFromUserInput(cal, true);		
			 booking = displayBooking(cal);
			 
			break;
		
		case 3:
			 cal = makeCalendarFromUserInput(cal, false);		
			 displayDayBookings(cal);
			break;
		
		case 0:
			System.out.println("Exiting Room Booking Application");
			
			break;
		
		default: 
			System.out.println("Bad input, exiting");	 
		}
			
	}
	
	
	private boolean saveRoomeBooking(RoomBooking booking) {
		boolean foundBooking = true;
		RoomBooking rmbooking = null;
		
		if (booking != null){
			rmbooking = findBooking(booking.getTimeblock().getStartTime());
		}
		
		if (rmbooking != null){
			foundBooking = false;
		}
		
		if (foundBooking == true){
			lastBookingIndex++;
			setBookingIndex(lastBookingIndex);
			roomBookings[getBookingIndex()]= booking;
			
		}
		
		return foundBooking;
	}
	
	private RoomBooking displayBooking(Calendar cal) {
		RoomBooking booking = null;
		booking = findBooking(cal);
		int hour = 0;
		
		if(booking != null) {
			System.out.println(booking.toString());
		}
		else{
			hour = cal.get(Calendar.HOUR_OF_DAY);;
			
			System.out.println("\nNo booking scheduled between "+ hour +":00" +" and " + (hour+1) + ":00\n");
		}
		
		return null;
	}
	
	private void displayDayBookings(Calendar cal){
		int hourBegin = 0;
		RoomBooking booking = null;
		
		cal.set(Calendar.HOUR_OF_DAY, 8);
		System.out.println();
		
		while(cal.get(Calendar.HOUR_OF_DAY)<=22){
		
			for(int i=0; i< roomBookings.length; i++){
				 booking = findBooking(cal);
			      
				 if (booking != null){
					 System.out.println(booking.toString());
					 hourBegin = booking.getTimeblock().getEndTime().get(Calendar.HOUR_OF_DAY);
					 cal.set(Calendar.HOUR_OF_DAY, hourBegin);
					 break;
				 }
			}
			
			if( booking == null){
				hourBegin = cal.get(Calendar.HOUR_OF_DAY);
				
				System.out.println("No booking scheduled between "+  hourBegin +":00 and " + (hourBegin+1)+":00");
				cal.set(Calendar.HOUR_OF_DAY, (hourBegin+1));
			}
		}
	}
	
	private static String getResponseTo(String s) 
	//source:Assignment 1 instruction version 1.11 p.6.
	{
				System.out.print(s);
				return getScan().nextLine();
	}
	
	private RoomBooking makeBookingFromUserInput(){
		ContactInfo contactInfo;
		Activity activity;
		TimeBlock timeBlock;
		Calendar cal1 = null;
		Calendar cal2 = null;
		boolean requestHour = true;
		
		RoomBooking booking = null;
		boolean savedBooking = false;
		
		String name = getResponseTo("Enter Client Name (as FirstName LastName ): ");	
		String number = getResponseTo("Phone Number (e.g. 613-555-1212): ");	
		String org = getResponseTo("Organization (optional): ");	
		String cat = getResponseTo("Enter event category: ");		
		String des = getResponseTo("Enter detailed description of event: ");
		
		
		if (org.isEmpty())
		//source:https://www.java67.com/2014/09/right-way-to-check-if-string-is-empty.html
		{
			contactInfo = new ContactInfo(name.split(" ")[0], name.split(" ")[1], number);
		}
		else{
			contactInfo = new ContactInfo(name.split(" ")[0], name.split(" ")[1], number, org);
		}
		
		
		activity = new Activity(cat,des);
		
		cal1 = makeCalendarFromUserInput(cal1, requestHour);
		cal2 =  new Calendar.Builder().setDate(cal1.get(Calendar.YEAR),cal1.get(Calendar.MONTH),cal1.get(Calendar.DAY_OF_MONTH)).build();
		cal2 = makeCalendarFromUserInput(cal2, requestHour);
		
		timeBlock = new TimeBlock(cal1, cal2);
		
		booking = new RoomBooking(timeBlock, contactInfo, activity);
	    	
		return booking;
	}
	
	
	private Calendar makeCalendarFromUserInput(Calendar cal, boolean requestHour){
		
		LocalDate date = null;
		Calendar.Builder calBuilder = null;
		String strDate = "" , time ="";
		int caltime = 0;
		
		
		if(cal == null)//source:https://dzone.com/articles/how-convert-string-date
			                  //https://www.baeldung.com/java-string-to-date
		{
		   
			strDate = getResponseTo("Event Date (entered as DDMMYYYY): ");
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy", Locale.ENGLISH);
			date = LocalDate.parse(strDate, formatter);
			
			
				
		/*	cal =    new Calendar.Builder()
			         .setDate(date.getYear(), date.getMonthValue(), date.getDayOfMonth()).build();
			     //    .setTimeOfDay(15, 45, 22)*
			      * 
			      */
			calBuilder = new Calendar.Builder();
			 calBuilder.setDate(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
			if (requestHour == true){
				time = getResponseTo("Start Time: ");
				caltime = processTimeString(time);
				calBuilder.setTimeOfDay(caltime, 0, 0);
		   		 
			}		
		
		}
		
		else{
			if (requestHour == true){
			
				time = getResponseTo("End Time: ");
				caltime = processTimeString(time);
				cal.set(Calendar.HOUR_OF_DAY, caltime);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				//calBuilder.setTimeOfDay(caltime, 0, 0);
			}
		}
		if (cal== null){	
		 cal = calBuilder.build();
		}
		
		return cal;
		
	}
	
	private static int processTimeString(String t) {
		String time24 = "";
		int timeint = 0;
		
		if (t.contains("a.m.")||t.contains("am")){
			time24 = t.split(" ")[0];
			timeint = Integer.parseInt(time24);
		}
		
		else if(t.contains("p.m.")||t.contains("pm")){
			time24 = t.split(" ")[0];
			if (time24.equals("12")){
				timeint = 12;
			}
			else{
			timeint = Integer.parseInt(time24) + 12;
			}
		}
		
		else{
			time24 = t.substring(0,t.indexOf(":"));
			timeint = Integer.parseInt(time24);
			
		}
		
		return timeint;
	}
	
	private RoomBooking findBooking(Calendar cal){
		
		int newhour = cal.get(Calendar.HOUR_OF_DAY) +1;
		//Calendar cal2 = cal;
		Calendar cal2 = new Calendar.Builder().setDate(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).setTimeOfDay(newhour, 0, 0).build();
	//	cal2.set(Calendar.HOUR_OF_DAY, newhour);
		//cal2.set(Calendar.MINUTE, 0);
	//	cal2.set(Calendar.SECOND, 0);
		TimeBlock timeBlock = new TimeBlock(cal, cal2);
		RoomBooking rmbooking = null;
		
		for(int i=0; i<roomBookings.length;i++){
			if (roomBookings[i] != null){			
				if (roomBookings[i].getTimeblock().overlaps(timeBlock)== true){
					rmbooking = roomBookings[i];
					break;
				}
			}
		}
		
		return rmbooking;
	}
	
	private RoomBooking[] getRoomBookings(){
		return roomBookings;
	}
	
	private int getBookingIndex(){
		return lastBookingIndex;
	}
	private void setBookingIndex(int bookingIndex){
		lastBookingIndex = bookingIndex;
	}

	public static Scanner getScan(){
		return scan;
	}

	public static void setScan(Scanner scan){
		RoomScheduler.scan = scan;
	}
	
	
}

