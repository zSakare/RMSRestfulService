package au.edu.unsw.soacourse.rms.data;

public class RenewalNotice {
	private CarRegistration registration;
	private Status status;
	
	public CarRegistration getRegistration() {
		return this.registration;
	}

	public void setRegistration(CarRegistration registration) {
		this.registration = registration;
	}
	
	public String getStatus() {
		return this.status != null ? this.status.getStatus() : "";
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
}
