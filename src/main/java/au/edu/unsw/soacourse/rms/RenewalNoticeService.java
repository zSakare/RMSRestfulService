package au.edu.unsw.soacourse.rms;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import au.edu.unsw.soacourse.rms.data.RenewalNotice;
import au.edu.unsw.soacourse.rms.datastore.RenewalNoticeDAO;

@Path("/renewal")
public class RenewalNoticeService {
	
	private RenewalNoticeDAO renewalNoticeDAO = new RenewalNoticeDAO();

    @GET
    @Path("/{rego}")
    @Produces("application/json")
    public Response getRenewalNotice(@PathParam("rego") String rego) {
    	RenewalNotice renewal = renewalNoticeDAO.retrieve(rego);
        return Response.ok().entity(renewal).build();
    }

    @POST
    @Path("/generate")
    @Produces("application/json")
    public Response generateRenewals() {
    	List<String> regosDue = renewalNoticeDAO.generate();
    	
    	for (int i = 0; i < regosDue.size(); i++) {
    		String rego = regosDue.get(i);
    		regosDue.set(i, "http://localhost:8080/RMSRestfulService/renewal/" + rego);
    	}
    	
        return Response.ok().entity(regosDue).build();
    }
}

