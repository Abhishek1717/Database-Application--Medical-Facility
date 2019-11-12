import java.util.*;
import java.sql.*;

public class patientCheckoutAcknowledgment {
	
	Connection conn = null;

	public patientCheckoutAcknowledgment(Connection con) {
		System.out.println("This is the Staff Patient Report Confirmation Page ");
		this.conn = con;
	}
	
	public void displayMenu() {
		
		//Display all the information 
		//to be given in the report by staff
		//See the initial pdf for the details, when implementing
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select EXP_ID, DISCHARGESTATUS, REFERRAL_FACILITY_ID, REFERRER_ID, TREATMENTGIVEN,  EXP_CODE, EXP_DESCRIPTION from EXPERIENCE");
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
		
		System.out.println("1. Yes");
		System.out.println("2. No");
		System.out.println("3. Go Back");
		
		Scanner input = new Scanner(System.in); 
		
		System.out.println("Enter your choice to fill : ");		
		int choice = input.nextInt();
		
		switch(choice) {
		
		case 1:
		{   return;
			
		}
		case 2:
		{   
			System.out.println("Please enter the reason");
			String reason=input.nextLine();
			
			break;
		}
		case 3:
		{   
			return;
			
		}
		default:
		{
			System.out.println("Please enter valid choice");
		}
		}
		
		input.close();
			
	}

}
