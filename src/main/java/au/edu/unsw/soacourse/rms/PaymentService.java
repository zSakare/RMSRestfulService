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

import au.edu.unsw.soacourse.rms.data.Payment;
import au.edu.unsw.soacourse.rms.data.RenewalNotice;
import au.edu.unsw.soacourse.rms.datastore.PaymentDAO;
import au.edu.unsw.soacourse.rms.datastore.RenewalNoticeDAO;

@Path("/payment")
public class PaymentService {
	
	@Autowired
	private RenewalNoticeDAO renewalNoticeDAO;
	
	@Autowired
	private PaymentDAO paymentDAO;
	
	@POST
	@Path("/new")
	@Produces("application/json")
	public Response generatePaymentNotice(@QueryParam("rego") String rego, @QueryParam("fee") String fee) throws URISyntaxException {
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
								@QueryParam("number") String number) {
		Payment payment = paymentDAO.retrievePayment(rego);
		payment.setCcExpiry(expiry);
		payment.setCcName(name);
		payment.setCcNumber(number);
		payment.setPaidDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		
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
