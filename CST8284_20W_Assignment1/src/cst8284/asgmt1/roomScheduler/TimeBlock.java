package cst8284.asgmt1.roomScheduler;

import java.util.Calendar;

public class TimeBlock 
//source:https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html#compareTo(java.util.Calendar)
{
	private Calendar startTime;
	private Calendar endTime;
	
	
	
	public TimeBlock() 
	//Source: Assignment 1 instruction version 1.11 p.11.
	{
		
		this(new Calendar.Builder().set(Calendar.HOUR, 8).build(),new Calendar.Builder().set(Calendar.HOUR, 23).build());
		
	}
	
	public TimeBlock(Calendar start, Calendar end){
	  startTime = start;
	  endTime = end;
	}
	
	public Calendar getStartTime(){
		return startTime;
	}
	
	public Calendar getEndTime(){
		return endTime;
	}
	
	public void setStartTime(Calendar startTime){
		this.startTime = startTime;
	}
	public void setEndTime(Calendar endTime){
		this.endTime = endTime;
	}
	
	public boolean overlaps(TimeBlock newBlock) 
	//source:https://www.geeksforgeeks.org/calendar-compareto-method-in-java-with-examples/
	{
		if(newBlock.getEndTime().compareTo(this.startTime)>0)
		{
			if(newBlock.getStartTime().compareTo(this.endTime)>=0)
			{
				return false;
			}
			else
			{
				return true;
				
			}
		}
		
		return false;
	}
	
	public int duration() 
	//source:https://stackoverflow.com/questions/5351483/calculate-date-time-difference-in-java
		   //https://stackoverflow.com/questions/1555262/calculating-the-difference-between-two-java-date-instances	
		   //https://www.tutorialspoint.com/java/util/calendar_gettimeinmillis.htm
	{
		int hours =0;
		long seconds =0;
	   
		seconds = (endTime.getTimeInMillis() - startTime.getTimeInMillis()) / 1000; 
		hours = (int) (seconds / 3600);  
		
		return hours;
	}
	
	public String toString()  
	//source:Assignment 1 instruction version 1.11 p.5.
    //https://www.geeksforgeeks.org/date-tostring-method-in-java-with-examples/
	{
		
		//return startTime.getTime().toString()  +" - " +  endTime.getTime().toString();
		return startTime.get(Calendar.HOUR_OF_DAY)   +":00 - " +  endTime.get(Calendar.HOUR_OF_DAY) +":00";
	}
}
