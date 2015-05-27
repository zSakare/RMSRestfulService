package au.edu.unsw.soacourse.rms;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import au.edu.unsw.soacourse.rms.data.FuelTypeRegistration;
import au.edu.unsw.soacourse.rms.data.PostcodeTypeRegistration;
import au.edu.unsw.soacourse.rms.datastore.CarRegistrationDataDAO;

@Path("/data")
public class CarRegistrationDataService {
	
	@Autowired
	CarRegistrationDataDAO carRegistrationDataDAO;
	
	@GET
	@Path("/allVehicleTypes")
	@Produces("application/json")
	public Response listAllVehicleTypes(@QueryParam("sortBy") String sorting) {
		List<String> vehicleTypes = carRegistrationDataDAO.listAllVehicleTypes();
		
		if ("descending".equals(sorting)) {
			Collections.sort(vehicleTypes, Collections.reverseOrder());
		} else if ("ascending".equals(sorting)) {
			Collections.sort(vehicleTypes);
		}
		
		return Response.ok().entity(vehicleTypes).build();
	}
	
	@GET
	@Path("/allFuelTypes")
	@Produces("application/json")
	public Response listAllFuelTypes(@QueryParam("sortBy") String sorting) {
		List<String> fuelTypes = carRegistrationDataDAO.listAllFuelTypes();
		
		if ("descending".equals(sorting)) {
			Collections.sort(fuelTypes, Collections.reverseOrder());
		} else if ("ascending".equals(sorting)) {
			Collections.sort(fuelTypes);
		}
		
		return Response.ok().entity(fuelTypes).build();
	}
	
	@GET
	@Path("/regosByFuelType")
	@Produces("application/json")
	public Response getRegosByFuelType(@QueryParam("type") String type, 
									   @QueryParam("sortBy") String sorting) {
		List<FuelTypeRegistration> fuelTypeRegos;
		
		if (type != null && !type.isEmpty()) {
			fuelTypeRegos = carRegistrationDataDAO.getRegosByFuelType(type);
		} else {
			fuelTypeRegos = carRegistrationDataDAO.getAllRegosByFuelType();
		}
		
		if ("descending".equals(sorting)) {
			Collections.sort(fuelTypeRegos, Collections.reverseOrder(new Comparator<FuelTypeRegistration>() {

				@Override
				public int compare(FuelTypeRegistration o1,
						FuelTypeRegistration o2) {
					return o1.getFuelType().compareTo(o2.getFuelType());
				}
				
			}));
		} else if ("ascending".equals(sorting)) {
			Collections.sort(fuelTypeRegos, new Comparator<FuelTypeRegistration>() {

				@Override
				public int compare(FuelTypeRegistration o1,
						FuelTypeRegistration o2) {
					return o1.getFuelType().compareTo(o2.getFuelType());
				}
				
			});
		}
		
		return Response.ok().entity(fuelTypeRegos).build();
	}
	
	@GET
	@Path("/regosByPostcode")
	@Produces("application/json")
	public Response getRegosByPostcode(@QueryParam("postcode") String postcode, 
									   @QueryParam("sortBy") String sorting) {
		List<PostcodeTypeRegistration> postcodeTypeRegistrations;
		
		if (postcode != null && !postcode.isEmpty()) {
			postcodeTypeRegistrations = carRegistrationDataDAO.getRegosByPostcode(postcode);
		} else {
			postcodeTypeRegistrations = carRegistrationDataDAO.getAllRegosByPostcode();
		}
		
		if ("descending".equals(sorting)) {
			Collections.sort(postcodeTypeRegistrations, Collections.reverseOrder(new Comparator<PostcodeTypeRegistration>() {

				@Override
				public int compare(PostcodeTypeRegistration o1,
						PostcodeTypeRegistration o2) {
					return Integer.compare(Integer.parseInt(o1.getPostcode()), Integer.parseInt(o2.getPostcode()));
				}
				
			}));
		} else if ("ascending".equals(sorting)) {
			Collections.sort(postcodeTypeRegistrations, new Comparator<PostcodeTypeRegistration>() {

				@Override
				public int compare(PostcodeTypeRegistration o1,
						PostcodeTypeRegistration o2) {
					return Integer.compare(Integer.parseInt(o1.getPostcode()), Integer.parseInt(o2.getPostcode()));
				}
				
			});
		}
		
		return Response.ok().entity(postcodeTypeRegistrations).build();
	}
	
	@GET
	@Path("/xml/fueltypes")
	@Produces("application/xml")
	public Response getFuelTypeXML() throws IOException {
		ClassLoader cl = this.getClass().getClassLoader();
		InputStream xml = cl.getResourceAsStream("outputfueltypes.xml");
		String xmlString = "";
		int i;
		char c;
		while ((i = xml.read()) != -1) {
			c = (char) i;
			xmlString += c;
		}
		
		return Response.ok().entity(xmlString).build();
	}
	
	@GET
	@Path("/xml/postcodes")
	@Produces("application/xml")
	public Response getPostcodeXML() throws IOException {
		ClassLoader cl = this.getClass().getClassLoader();
		InputStream xml = cl.getResourceAsStream("outputpostcodes.xml");
		String xmlString = "";
		int i;
		char c;
		while ((i = xml.read()) != -1) {
			c = (char) i;
			xmlString += c;
		}
		
		return Response.ok().entity(xmlString).build();
	}
	
	@GET
	@Path("/xml/allVehicleTypes")
	@Produces("application/xml")
	public Response listAllVehicleTypesXML(@QueryParam("sortBy") String sorting) {
		String responseString = carRegistrationDataDAO.getAllVehicleTypesXML(sorting);
		
		return Response.ok().entity(responseString).build();
	}
}
