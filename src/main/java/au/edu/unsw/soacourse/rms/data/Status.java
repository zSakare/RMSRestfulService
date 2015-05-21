package au.edu.unsw.soacourse.rms.data;

public enum Status {
	CREATED("created"), 
	CANCELLED("cancelled"), 
	REQUESTED("requested"), 
	UNDER_REVIEW("under-review"), 
	ACCEPTED("accepted"), 
	ARCHIVED("archived"), 
	REJECTED("rejected");
	
	private final String status;
	
	private Status(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
}
