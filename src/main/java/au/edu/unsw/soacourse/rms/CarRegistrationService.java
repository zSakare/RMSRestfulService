package au.edu.unsw.soacourse.rms;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import au.edu.unsw.soacourse.rms.data.CarRegistration;
import au.edu.unsw.soacourse.rms.datastore.CarRegistrationDAO;

@Path("/rego")
public class CarRegistrationService {
	
	@Autowired
	private CarRegistrationDAO carRegistrationDAO;
	
	@GET
	@Path("/check")
	@Produces("application/json")
	public Response checkRego(@QueryParam("rego") String rego) {
		CarRegistration registration = carRegistrationDAO.retrieveRegistration(rego);
		return Response.ok().entity(registration).build();
	}
	
	@PUT
	@Path("/renew")
	@Produces("application/json")
	public Response updateRego(@QueryParam("rego") String rego) {
		CarRegistration registration = carRegistrationDAO.retrieveRegistration(rego);
		registration.setLastRegistered(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		
		return Response.ok().entity(registration).build();
	}
}
