package au.edu.unsw.soacourse.rms.data;

public class CarRegistration {
	private String rid;
	private String registrationNumber;
	private Driver driver;
	private String validTill;
	private String lastRegistered;
	
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
	
	public String getLastRegistered() {
		return this.lastRegistered;
	}
	
	public void setLastRegistered(String lastRegistered) {
		this.lastRegistered = lastRegistered;
	}
}
