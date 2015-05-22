package au.edu.unsw.soacourse.rms.data;

public class Payment {
	private RenewalNotice renewal;
	private String amount;
	private String ccName;
	private String ccExpiry;
	private String ccNumber;
	private String paidDate;

	public RenewalNotice getRenewal() {
		return renewal;
	}
	
	public void setRenewal(RenewalNotice renewal) {
		this.renewal = renewal;
	}
	
	public String getAmount() {
		return amount;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getCcName() {
		return ccName;
	}
	
	public void setCcName(String ccName) {
		this.ccName = ccName;
	}
	
	public String getCcExpiry() {
		return ccExpiry;
	}
	
	public void setCcExpiry(String ccExpiry) {
		this.ccExpiry = ccExpiry;
	}
	
	public String getCcNumber() {
		return ccNumber;
	}
	
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
	
	public String getPaidDate() {
		return paidDate;
	}
	
	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}
}
