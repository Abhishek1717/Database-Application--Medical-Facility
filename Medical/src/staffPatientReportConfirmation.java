import java.util.*;
import java.sql.*;

public class staffPatientReportConfirmation {
	
	Connection conn = null;
	int patientId;
	int facilityId;
	
	public staffPatientReportConfirmation(Connection con, int patId, int facId) {
		System.out.println("This is the Staff Patient Report Confirmation Page ");
		this.conn = con;
		this.patientId = patId;
		this.facilityId = facId;
	}
	
	public int displayMenu() {
		
		//Display all the information to be given in the report
		//See the initial pdf for the details, when implementing
		
		
		/////////////////SQL procedure to check if all the report components are done
		try {
			Statement stmt = conn.createStatement();
			int checkinId = stmt.executeQuery("select CHECKIN_ID from LOG_IN,  where PATIENT_ID = " + patientId + "and FACILITY_ID = " + facilityId ).getInt("CHECKIN_ID");
			ResultSet rs = stmt.executeQuery("Select EXP_ID, DISCHARGESTATUS, REFERRAL_FACILITY_ID, REFERRER_ID, TREATMENTGIVEN,  EXP_CODE, EXP_DESCRIPTION from EXPERIENCE "
					+ "where PATIENT_ID = " + patientId + " CHECKINID = " + checkinId);
			int exp_id = rs.getInt("EXP_ID");
			int fac_id = rs.getInt("REFERRAL_FACILITY_ID");
			String disSta = rs.getString("DISCHARGESTATUS");
			String refID = rs.getString("REFERRER_ID");
			String tg = rs.getString("TREATMENTGIVEN");
			String exp_code = rs.getString("EXP_CODE");
			String exp_de = rs.getString("EXP_DESCRIPTION");
			System.out.println("1. Discharge Status: " + disSta);
			
			if(disSta.equals("Referred")) {
				ResultSet rq = stmt.executeQuery("Select REASON_CODE, SERVICE_CODE, DESCRIPTION from REASONS where EXP_ID = " + exp_id);
				System.out.println(" Referral Status:");
				System.out.println(" Referrer: " + refID );
				if(fac_id != 0) {
					ResultSet rr = stmt.executeQuery("Select NAME from MEDICAL_FACILITY where FACILITY_ID = " + fac_id);
					System.out.println(" Referred Facility: " + rr.getString("NAME"));
				}
				else {
					System.out.println(" Referred Facility: None" );
				}
				System.out.println(" Reasons:");
				while(rq.next()) {
					int reason = rq.getInt("REASON_CODE");
					if(reason == 1) {
						System.out.println("Service unavailable at time of visit");
					}
					else if(reason == 2) {
						System.out.println("Service not present at facility");
					}
					else if(reason == 3) {
						System.out.println("Non-payment");
					}
					String service = rq.getString("SERVICE_CODE");
					if(service != null) {
						ResultSet rt = stmt.executeQuery("Select SERVICE_NAME from SERVICES where SERVICE_CODE = " + service);
						System.out.println("Name of Service : " + rt.getString("SERVICE_NAME"));
					}
					else {
						System.out.println("Name of Service : Other");
					}
					System.out.println("Description : " +  rq.getString("DESCRIPTION"));
					
				}
			}
				
				System.out.println("Treatement given: " + tg);
				
				if(exp_code.equals("1")) {
					System.out.println("Negative Experience: Misdiagnosis ");
					
				}
				else if(exp_code.equals("2")) {
					System.out.println("Negative Experience: Patient acquired infection during hospital stay");
				}
				System.out.println("Negative Experience Description: " + exp_de);
				
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("1. Confirm");
		System.out.println("2. Go Back");
		
		Scanner input = new Scanner(System.in); 
		
		System.out.println("Enter your choice to fill : ");		
		int choice = input.nextInt();
		while(true) {
		switch(choice) {
		
		case 1:
		{   
			System.out.println("Report Confirmed");
			return 1;
		    	
		
		}
		case 2:
		{
			System.out.println("Redirecting back to Staff-Patient Report");
			return 0;
			
		}
		default:
		{
			System.out.println("Please enter valid choice");
		}
		}
		
		return 1;
		}
		
		
	}

}
