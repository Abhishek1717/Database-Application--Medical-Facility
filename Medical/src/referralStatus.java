import java.util.*;
import java.sql.*;

public class referralStatus {
	int refFacilityId = -1;
	int ReferrerId = -1;
	int facilityId;
	int patientId;
	String reason;
	Connection conn = null;
	public referralStatus(Connection con, int patId, int facId, int empId) {
		System.out.println("This is the referral status page");
		this.ReferrerId = empId;
		this.conn = con;
		this.patientId = patId;
		this.facilityId = facId;
		this.ReferrerId = empId;
	}
	
	public void displayMenu() {
		
		Scanner input = new Scanner(System.in); 
		while(true)
		{
		System.out.println("1. Facility ID");
		System.out.println("2. Referrer ID");
		System.out.println("3. Add reason");
		System.out.println("4. Go Back");
		
		
		///  add to experience table......//////////
		
		System.out.println("Enter your choice to fill : ");		
		int choice = input.nextInt();
		input.nextLine();
		
		switch(choice) {
		
		case 1:
		{
			
			System.out.println("Enter Facility ID");
			refFacilityId=input.nextInt();
			input.nextLine();
			
			CallableStatement cstmt;
			try {
				cstmt = conn.prepareCall("{CALL UpdateReferrerDetails(?,?,?,?)}");
				
				cstmt.setInt(1, patientId);
		    	cstmt.setInt(2, facilityId);
		    	cstmt.setInt(3, ReferrerId);
		    	cstmt.setInt(4, refFacilityId);
		    	cstmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			break;
		}
		case 2:
		{
			
			System.out.println("Enter Referrer ID");
			refFacilityId=input.nextInt();
			input.nextLine();
			
			CallableStatement cstmt;
			try {
				cstmt = conn.prepareCall("{CALL UpdateReferrerDetails(?,?,?,?)}");
				
				cstmt.setInt(1, patientId);
		    	cstmt.setInt(2, facilityId);
		    	cstmt.setInt(3, ReferrerId);
		    	cstmt.setInt(4, refFacilityId);
		    	cstmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case 3:
		{   
			referralReason rr= new referralReason(conn, patientId, facilityId);
			rr.displayMenu();
			break;
		}
		case 4:
		{   
			System.out.println("Redirecting back to patient Staff patient report");
			return;
		}
		default:
		{
			System.out.println("Please enter valid choice");
		}
		}
		
	}
	}

}
