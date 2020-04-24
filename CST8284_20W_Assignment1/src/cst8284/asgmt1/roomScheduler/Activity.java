package cst8284.asgmt1.roomScheduler;

public class Activity {
	private String description;
	private String category;
	
	
	public Activity(String cat, String des) {
		description = des;
		category = cat;
	}
	public String getDescriptionOfWork() {
		return description;
	}
	public String getCategory() {
		return category;
	}
	public void setDescriptionOfWork(String description ) {
		this.description = description;
	}
	public void setCategory(String category ) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return  "Event: " + getCategory() + "\n" + 
			((getDescriptionOfWork()!="")?"Description: " + getDescriptionOfWork():"") + "\n";
	}
}
