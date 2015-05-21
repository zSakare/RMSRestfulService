package au.edu.unsw.soacourse.rms;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import au.edu.unsw.soacourse.rms.data.RenewalNotice;
import au.edu.unsw.soacourse.rms.data.Status;
import au.edu.unsw.soacourse.rms.datastore.RenewalNoticeDAO;

@Path("/renewal")
public class RenewalNoticeService {
	
	private RenewalNoticeDAO renewalNoticeDAO = new RenewalNoticeDAO();

    @GET
    @Path("/notice")
    @Produces("application/json")
    public Response getRenewalNotice(@QueryParam("rego") String rego) {
    	RenewalNotice renewal = renewalNoticeDAO.retrieve(rego);
        return Response.ok().entity(renewal).build();
    }

    @POST
    @Path("/notice/generate")
    @Produces("application/json")
    public Response generateRenewals() {
    	List<String> regosDue = renewalNoticeDAO.generate();
    	
    	for (int i = 0; i < regosDue.size(); i++) {
    		String rego = regosDue.get(i);
    		regosDue.set(i, "http://localhost:8080/RMSRestfulService/renewal/notice?rego=" + rego);
    	}
    	
        return Response.ok().entity(regosDue).build();
    }
    
    @PUT
    @Path("/notice/update")
    @Produces("application/json")
    public Response updateRenewal(@QueryParam("rego") String rego, @QueryParam("status") String status) {
    	RenewalNotice renewal = renewalNoticeDAO.retrieve(rego);
    	renewal.setStatus(Status.valueOf(status));
    	
    	return Response.ok().entity(renewal).build();
    }
    
    @DELETE
    @Path("/notice/archive")
    @Produces("application/json")
    public Response archiveRenewal(@QueryParam("rego") String rego) {
    	RenewalNotice renewal = renewalNoticeDAO.retrieve(rego);
    	if (renewal.getStatus().equals("rejected") || renewal.getStatus().equals("cancelled")) {
    		renewal.setStatus(Status.ARCHIVED);
    	}
    	
    	return Response.ok().build();
    }
}

