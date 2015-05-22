package au.edu.unsw.soacourse.rms.datastore;

import java.util.ArrayList;
import java.util.List;

import au.edu.unsw.soacourse.rms.data.Payment;

public class PaymentDAO {
	private List<Payment> payments = new ArrayList<Payment>();
	
	public void createPayment(Payment payment) {
		payments.add(payment);
	}

	public Payment retrievePayment(String rego) {
		for (Payment p : payments) {
			if (p.getRenewal().getRegistration().getRegistrationNumber().equals(rego)) {
				return p;
			}
		}
		
		return null;
	}
}
