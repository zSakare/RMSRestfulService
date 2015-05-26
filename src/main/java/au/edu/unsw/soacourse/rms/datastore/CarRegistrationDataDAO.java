package au.edu.unsw.soacourse.rms.datastore;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import au.edu.unsw.soacourse.rms.data.FuelTypeRegistration;
import au.edu.unsw.soacourse.rms.data.PostcodeTypeRegistration;

public class CarRegistrationDataDAO {
	private List<String> vehicleTypes = new ArrayList<String>();
	private List<String> fuelTypes = new ArrayList<String>();
	private List<PostcodeTypeRegistration> postcodeTypeRegistrations = new ArrayList<PostcodeTypeRegistration>();
	private List<FuelTypeRegistration> fuelTypeRegistrations = new ArrayList<FuelTypeRegistration>();
	
	public CarRegistrationDataDAO() {
		vehicleTypes.add("Passenger Vehicles");
		vehicleTypes.add("Off-Road Vehicles");
		vehicleTypes.add("People Movers");
		vehicleTypes.add("Small Buses");
		vehicleTypes.add("Buses");
		vehicleTypes.add("Mobile Homes");
		vehicleTypes.add("Motor Cycles");
		vehicleTypes.add("Scooters");
		vehicleTypes.add("Light Trucks");
		vehicleTypes.add("Heavy Trucks");
		vehicleTypes.add("Prime Movers");
		vehicleTypes.add("Light Plants");
		vehicleTypes.add("Heavy Plants");
		vehicleTypes.add("Small Trailers");
		vehicleTypes.add("Trailers");
		
		fuelTypes.add("Unleaded Petrol");
		fuelTypes.add("LPG");
		fuelTypes.add("Electric/Petrol");
		fuelTypes.add("Electricity");
		fuelTypes.add("Petrol And LPG (Dual Fuel)");
		fuelTypes.add("Diesel");
		fuelTypes.add("Diesel And LPG (Dual Fuel)");
		fuelTypes.add("Diesel & LPT (Torque Topping)");
		fuelTypes.add("Diesel/NAT");
		fuelTypes.add("Compressed Natural Gas");
		fuelTypes.add("Petrol & Compressed Natural Gas");
		fuelTypes.add("Hydrogen");
		fuelTypes.add("Kerosene");
		fuelTypes.add("Steam (Fuel Oil Powered)");
		fuelTypes.add("Unknown");
		fuelTypes.add("No engine");
		
		generatePostcodeTypeRegistrations();
		generateFuelTypeRegistrations();
	}
	
	public List<String> listAllVehicleTypes() {
		List<String> tempList = new ArrayList<String>(vehicleTypes);
		return tempList;
	}
	
	public List<String> listAllFuelTypes() {
		List<String> tempList = new ArrayList<String>(fuelTypes);
		return tempList;
	}

	public List<FuelTypeRegistration> getAllRegosByFuelType() {
		return this.fuelTypeRegistrations;
	}

	public List<FuelTypeRegistration> getRegosByFuelType(String type) {
		List<FuelTypeRegistration> filteredList = new ArrayList<FuelTypeRegistration>();
		for (FuelTypeRegistration fuelType : fuelTypeRegistrations) {
			if (fuelType.getFuelType().contains(type)) {
				filteredList.add(fuelType);
			}
		}
		return filteredList;
	}

	public List<PostcodeTypeRegistration> getAllRegosByPostcode() {
		return this.postcodeTypeRegistrations;
	}

	public List<PostcodeTypeRegistration> getRegosByPostcode(String postcode) {
		List<PostcodeTypeRegistration> filteredList = new ArrayList<PostcodeTypeRegistration>();
		
		postcode = postcode.replace('X', '.');
		
		while (postcode.length() < 4) {
			postcode += ".";
		}
		
		for (PostcodeTypeRegistration postcodeRego : postcodeTypeRegistrations) {
			if (postcodeRego.getPostcode().matches(postcode)) {
				filteredList.add(postcodeRego);
			}
		}
		
		return filteredList;
	}
	
	private NodeList getPostcodeRegistrationEntries() {
		ClassLoader cl = this.getClass().getClassLoader();
		NodeList nodes = null;
		InputStream renewalXML = cl.getResourceAsStream("outputpostcodes.xml");
		 
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(renewalXML);
			
			nodes = doc.getElementsByTagName("Entry");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nodes;
	}
	
	private NodeList getFuelTypeRegistrationEntries() {
		ClassLoader cl = this.getClass().getClassLoader();
		NodeList nodes = null;
		InputStream renewalXML = cl.getResourceAsStream("outputfueltypes.xml");
		 
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(renewalXML);
			
			nodes = doc.getElementsByTagName("Entry");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nodes;
	}
	
	private void generatePostcodeTypeRegistrations() {
		NodeList nodes = getPostcodeRegistrationEntries();
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				
				try {
					PostcodeTypeRegistration postcodeTypeRegistration = new PostcodeTypeRegistration();
					postcodeTypeRegistration.setPostcode(element.getElementsByTagName("PostCode").item(0).getTextContent());
					postcodeTypeRegistration.setTotal(element.getElementsByTagName("Total").item(0).getTextContent());
					postcodeTypeRegistration.setPassengerVehicle(element.getElementsByTagName("PassengerVehicles").item(0).getTextContent());
					postcodeTypeRegistration.setOffRoadVehicles(element.getElementsByTagName("OffRoadVehicles").item(0).getTextContent());
					postcodeTypeRegistration.setPeopleMovers(element.getElementsByTagName("PeopleMovers").item(0).getTextContent());
					postcodeTypeRegistration.setSmallBuses(element.getElementsByTagName("SmallBuses").item(0).getTextContent());
					postcodeTypeRegistration.setBuses(element.getElementsByTagName("Buses").item(0).getTextContent());
					postcodeTypeRegistration.setMobileHomes(element.getElementsByTagName("MobileHomes").item(0).getTextContent());
					postcodeTypeRegistration.setMotorCycles(element.getElementsByTagName("MotorCycles").item(0).getTextContent());
					postcodeTypeRegistration.setScooters(element.getElementsByTagName("Scooters").item(0).getTextContent());
					postcodeTypeRegistration.setLightTrucks(element.getElementsByTagName("LightTrucks").item(0).getTextContent());
					postcodeTypeRegistration.setHeavyTrucks(element.getElementsByTagName("HeavyTrucks").item(0).getTextContent());
					postcodeTypeRegistration.setPrimeMovers(element.getElementsByTagName("PrimeMovers").item(0).getTextContent());
					postcodeTypeRegistration.setLightPlants(element.getElementsByTagName("LightPlants").item(0).getTextContent());
					postcodeTypeRegistration.setHeavyPlants(element.getElementsByTagName("HeavyPlants").item(0).getTextContent());
					postcodeTypeRegistration.setSmallTrailers(element.getElementsByTagName("SmallTrailers").item(0).getTextContent());
					postcodeTypeRegistration.setTrailers(element.getElementsByTagName("Trailers").item(0).getTextContent());
					
					postcodeTypeRegistrations.add(postcodeTypeRegistration);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void generateFuelTypeRegistrations() {
		NodeList nodes = getFuelTypeRegistrationEntries();
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				
				try {
					FuelTypeRegistration fuelTypeRegistration = new FuelTypeRegistration();
					fuelTypeRegistration.setFuelType(element.getElementsByTagName("FuelType").item(0).getTextContent());
					fuelTypeRegistration.setTotal(element.getElementsByTagName("Total").item(0).getTextContent());
					fuelTypeRegistration.setPassengerVehicle(element.getElementsByTagName("PassengerVehicles").item(0).getTextContent());
					fuelTypeRegistration.setOffRoadVehicles(element.getElementsByTagName("OffRoadVehicles").item(0).getTextContent());
					fuelTypeRegistration.setPeopleMovers(element.getElementsByTagName("PeopleMovers").item(0).getTextContent());
					fuelTypeRegistration.setSmallBuses(element.getElementsByTagName("SmallBuses").item(0).getTextContent());
					fuelTypeRegistration.setBuses(element.getElementsByTagName("Buses").item(0).getTextContent());
					fuelTypeRegistration.setMobileHomes(element.getElementsByTagName("MobileHomes").item(0).getTextContent());
					fuelTypeRegistration.setMotorCycles(element.getElementsByTagName("MotorCycles").item(0).getTextContent());
					fuelTypeRegistration.setScooters(element.getElementsByTagName("Scooters").item(0).getTextContent());
					fuelTypeRegistration.setLightTrucks(element.getElementsByTagName("LightTrucks").item(0).getTextContent());
					fuelTypeRegistration.setHeavyTrucks(element.getElementsByTagName("HeavyTrucks").item(0).getTextContent());
					fuelTypeRegistration.setPrimeMovers(element.getElementsByTagName("PrimeMovers").item(0).getTextContent());
					fuelTypeRegistration.setLightPlants(element.getElementsByTagName("LightPlants").item(0).getTextContent());
					fuelTypeRegistration.setHeavyPlants(element.getElementsByTagName("HeavyPlants").item(0).getTextContent());
					fuelTypeRegistration.setSmallTrailers(element.getElementsByTagName("SmallTrailers").item(0).getTextContent());
					fuelTypeRegistration.setTrailers(element.getElementsByTagName("Trailers").item(0).getTextContent());
					
					fuelTypeRegistrations.add(fuelTypeRegistration);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
