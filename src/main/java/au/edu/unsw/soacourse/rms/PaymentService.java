package au.edu.unsw.soacourse.rms;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import au.edu.unsw.soacourse.rms.data.CarRegistration;
import au.edu.unsw.soacourse.rms.data.Payment;
import au.edu.unsw.soacourse.rms.data.RenewalNotice;
import au.edu.unsw.soacourse.rms.data.Status;
import au.edu.unsw.soacourse.rms.datastore.CarRegistrationDAO;
import au.edu.unsw.soacourse.rms.datastore.PaymentDAO;
import au.edu.unsw.soacourse.rms.datastore.RenewalNoticeDAO;

@Path("/payment")
public class PaymentService {
	private final static String OFFICER_KEY = "officer";
	private final static String DRIVER_KEY = "driver";
	
	@Autowired
	private RenewalNoticeDAO renewalNoticeDAO;
	
	@Autowired
	private PaymentDAO paymentDAO;

	@Autowired
	private CarRegistrationDAO carRegistrationDAO;
	
	@POST
	@Path("/new")
	@Produces("application/json")
	public Response generatePaymentNotice(@QueryParam("rego") String rego, @QueryParam("fee") String fee, @QueryParam("auth") String key) throws URISyntaxException {
		if (key == null || key.isEmpty()) {
			return Response.status(401).build();
		} else if (!key.equals(OFFICER_KEY)) {
			return Response.status(403).build();
		}
		
		Payment payment = new Payment();
		payment.setAmount(fee);
		
		RenewalNotice renewal = renewalNoticeDAO.retrieve(rego);
		if (renewal != null) {
			payment.setRenewal(renewal);
			paymentDAO.createPayment(payment);
		} else {
			// Couldn't find renewal.
			return Response.status(404).build();
		}
		
		return Response.created(new URI("http://localhost:8080/RMSRestfulService/payment/check?rego=" + rego)).entity(payment).build();
	}
	
	@PUT
	@Path("/pay")
	@Produces("application/json")
	public Response makePayment(@QueryParam("rego") String rego, 
								@QueryParam("name") String name, 
								@QueryParam("expiry") String expiry, 
								@QueryParam("number") String number,
								@QueryParam("auth") String key) {
		if (key == null || key.isEmpty()) {
			return Response.status(401).build();
		} else if (!key.equals(DRIVER_KEY)) {
			return Response.status(403).build();
		}
		
		Payment payment = paymentDAO.retrievePayment(rego);
		payment.setCcExpiry(expiry);
		payment.setCcName(name);
		payment.setCcNumber(number);
		payment.setPaidDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		payment.getRenewal().setStatus(Status.PAID);
		
		CarRegistration carRego = carRegistrationDAO.retrieveRegistration(rego);
		carRego.setLastRegistered(payment.getPaidDate());
		
		return Response.ok().entity(payment).build();
	}
	
	@GET
	@Path("/check")
	@Produces("application/json")
	public Response checkPayment(@QueryParam("rego") String rego) {
		Payment payment = paymentDAO.retrievePayment(rego);
		
		if (payment != null) {
			return Response.ok().entity(payment).build();	
		} else {
			return Response.status(404).build();
		}
	}
}
