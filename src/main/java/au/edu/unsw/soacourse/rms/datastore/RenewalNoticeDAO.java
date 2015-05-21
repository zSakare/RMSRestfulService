package au.edu.unsw.soacourse.rms.datastore;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import au.edu.unsw.soacourse.rms.data.Driver;
import au.edu.unsw.soacourse.rms.data.RenewalNotice;

public class RenewalNoticeDAO {
	
	public RenewalNotice retrieve(String rego) {
		RenewalNotice rNotice = new RenewalNotice();
	
		NodeList nodes = getRenewalEntries();
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				
				if (element.getElementsByTagName("RegistrationNumber").item(0).getTextContent().equals(rego)) {
					
					Element driverEl = (Element) element.getElementsByTagName("Driver").item(0);
					Driver driver = new Driver();
					driver.setLastName(driverEl.getElementsByTagName("LastName").item(0).getTextContent());
					driver.setFirstName(driverEl.getElementsByTagName("FirstName").item(0).getTextContent());
					driver.setEmail(driverEl.getElementsByTagName("Email").item(0).getTextContent());
					driver.setLicenseNumber(driverEl.getElementsByTagName("DriversLicenseNo").item(0).getTextContent());
					
					rNotice.setDriver(driver);
					rNotice.setRid(element.getElementsByTagName("_rid").item(0).getTextContent());
					rNotice.setValidTill(element.getElementsByTagName("RegistrationValidTill").item(0).getTextContent());
					rNotice.setRegistrationNumber(element.getElementsByTagName("RegistrationNumber").item(0).getTextContent());
				}
			}
		}
		
		return rNotice;
	}
	
	public List<String> generate() {
		List<String> regosDue = new ArrayList<String>();
		NodeList nodes = getRenewalEntries();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				
				try {
					Date regoDate = sdf.parse(element.getElementsByTagName("RegistrationValidTill").item(0).getTextContent());
					Date currentDate = new Date();
					
					Calendar cal = Calendar.getInstance();
					cal.setTime(regoDate);
					cal.add(Calendar.MONTH, -1);
					Date oneMonthBeforeRego = cal.getTime();
					
					if (currentDate.after(oneMonthBeforeRego) && currentDate.before(regoDate)) {
						regosDue.add(element.getElementsByTagName("RegistrationNumber").item(0).getTextContent());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return regosDue;
	}
	
	private NodeList getRenewalEntries() {
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
}
