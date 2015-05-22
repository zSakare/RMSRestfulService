package au.edu.unsw.soacourse.rms.datastore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import au.edu.unsw.soacourse.rms.data.CarRegistration;
import au.edu.unsw.soacourse.rms.data.Driver;

public class CarRegistrationDAO {
	
	private List<CarRegistration> registrations = new ArrayList<CarRegistration>();
	
	public CarRegistrationDAO() {
		registrations = generateRegistrations();
	}

	public CarRegistration retrieveRegistration(String rego) {
		for (CarRegistration carRego : registrations) {
			if (carRego.getRegistrationNumber().equals(rego)) {
				return carRego;
			}
		}
		
		return null;
	}

	public List<CarRegistration> getRegistrations() {
		return this.registrations;
	}
	
	private NodeList getRegistrationEntries() {
		NodeList nodes = null;
		File renewalXML = new File(System.getProperty("catalina.home") + File.separator +
								   "webapps" + File.separator +
								   "ROOT" + File.separator +
								   "database" + File.separator +
								   "renewals.xml");
		 
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
	
	private List<CarRegistration> generateRegistrations() {
		NodeList nodes = getRegistrationEntries();
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				
				try {
					CarRegistration registration = new CarRegistration();
					Element driverEl = (Element) element.getElementsByTagName("Driver").item(0);
					Driver driver = new Driver();
					driver.setLastName(driverEl.getElementsByTagName("LastName").item(0).getTextContent());
					driver.setFirstName(driverEl.getElementsByTagName("FirstName").item(0).getTextContent());
					driver.setEmail(driverEl.getElementsByTagName("Email").item(0).getTextContent());
					driver.setLicenseNumber(driverEl.getElementsByTagName("DriversLicenseNo").item(0).getTextContent());
					
					registration.setDriver(driver);
					registration.setRid(element.getElementsByTagName("_rid").item(0).getTextContent());
					registration.setValidTill(element.getElementsByTagName("RegistrationValidTill").item(0).getTextContent());
					registration.setRegistrationNumber(element.getElementsByTagName("RegistrationNumber").item(0).getTextContent());
					registrations.add(registration);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return registrations;
	}
}
