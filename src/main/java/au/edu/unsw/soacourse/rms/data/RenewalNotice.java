package au.edu.unsw.soacourse.rms.data;

import java.util.Date;

public class RenewalNotice {
	private String rid;
	private String registrationNumber;
	private Driver driver;
	private String validTill;
	private Status status;
	
	public String getRid() {
		return rid;
	}
	
	public void setRid(String rid) {
		this.rid = rid;
	}
	
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	
	public Driver getDriver() {
		return driver;
	}
	
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	public String getValidTill() {
		return validTill;
	}
	
	public void setValidTill(String validTill) {
		this.validTill = validTill;
	}
	
	public String getStatus() {
		return this.status != null ? this.status.getStatus() : "";
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
}
