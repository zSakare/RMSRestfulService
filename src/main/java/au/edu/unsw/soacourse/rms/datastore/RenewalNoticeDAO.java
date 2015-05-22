package au.edu.unsw.soacourse.rms.datastore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import au.edu.unsw.soacourse.rms.data.CarRegistration;
import au.edu.unsw.soacourse.rms.data.RenewalNotice;
import au.edu.unsw.soacourse.rms.data.Status;

public class RenewalNoticeDAO {
	
	@Autowired
	private CarRegistrationDAO carRegistrationDAO;
	
	private List<RenewalNotice> renewals = new ArrayList<RenewalNotice>();

	public RenewalNotice retrieve(String rego) {
		for (RenewalNotice renewal : renewals) {
			if (renewal.getRegistration().getRegistrationNumber().equals(rego)) {
				return renewal;
			}
		}
		
		return null;
	}
	
	public List<String> generate() throws ParseException {
		List<String> regosDue = new ArrayList<String>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		List<CarRegistration> regos = carRegistrationDAO.getRegistrations();
		for (int i = 0; i < regos.size(); i++) {
			Date regoDate = sdf.parse(regos.get(i).getValidTill());
			Date currentDate = new Date();
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(regoDate);
			cal.add(Calendar.MONTH, -1);
			Date oneMonthBeforeRego = cal.getTime();
			
			if (currentDate.after(oneMonthBeforeRego) && currentDate.before(regoDate)) {
				RenewalNotice rNotice = new RenewalNotice();
				rNotice.setRegistration(regos.get(i));
				rNotice.setStatus(Status.CREATED);
				renewals.add(rNotice);
				
				regosDue.add(rNotice.getRegistration().getRegistrationNumber());
			}
		}
		return regosDue;
	}
}
