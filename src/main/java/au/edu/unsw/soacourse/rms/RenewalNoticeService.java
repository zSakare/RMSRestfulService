package au.edu.unsw.soacourse.rms;
import java.text.ParseException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import au.edu.unsw.soacourse.rms.data.RenewalNotice;
import au.edu.unsw.soacourse.rms.data.Status;
import au.edu.unsw.soacourse.rms.datastore.RenewalNoticeDAO;

@Path("/renewal")
public class RenewalNoticeService {
	
	@Autowired
	private RenewalNoticeDAO renewalNoticeDAO;

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
    public Response generateRenewals() throws ParseException {
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
    	
    	if (status != null && !status.isEmpty()) {
    		Status newStatus = Status.valueOf(status);
    		if (!newStatus.equals(Status.ARCHIVED) && "archived".equals(renewal.getStatus())) {
    			// cannot set status after archiving.
        		return Response.notModified().entity(renewal).build();
    		} else if (newStatus.equals(Status.CANCELLED) && renewal.getStatus().equals("under-review")) {
    			// cannot cancel while under review.
        		return Response.notModified().entity(renewal).build();
    		} else {
    			renewal.setStatus(newStatus);
    		}
    	} else {
    		return Response.notModified().entity(renewal).build();
    	}
    	
    	return Response.ok().entity(renewal).build();
    }
    
    @DELETE
    @Path("/notice/archive")
    @Produces("application/json")
    public Response archiveRenewal(@QueryParam("rego") String rego) {
    	RenewalNotice renewal = renewalNoticeDAO.retrieve(rego);
    	if (renewal.getStatus().equals("rejected") || renewal.getStatus().equals("cancelled")) {
    		renewal.setStatus(Status.ARCHIVED);
    	} else {
    		return Response.notModified().build();
    	}
    	
    	return Response.ok().build();
    }
}

