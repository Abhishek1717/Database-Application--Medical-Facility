import java.util.*;
import java.sql.*;

public class referralReason {
	
	Connection conn = null;
	String serviceName,Description;
	int patientId;
	int facilityId;
	int reasonCode;
	
	public referralReason(Connection con, int patId, int facId) {
		System.out.println("This is the referral reason page");
		
		this.conn = con;
		this.patientId = patId;
		this.facilityId = facId;
	}
	
	public void displayMenu() {
		//Display the reason code, 
		//the name of the service 
		//and description
		int i=0;
		Scanner input = new Scanner(System.in); 
	
		System.out.println("1. Name of Service");
		System.out.println("2. Description");
		System.out.println("3. Record Reason");
		System.out.println("4. Go Back");
		
		while(true)
		{
		System.out.println("Enter your choice to fill : ");		
		int choice = input.nextInt();
		
		switch(choice) {
		
		case 1:
		{   System.out.println("Enter Name of Service");
		    serviceName=input.nextLine();
			break;
		}
		case 2:
		{    System.out.println(" Enter description");
			 Description = input.nextLine();
			
			break;
		}
		case 3:
		{   
			System.out.println(" Enter reason (1,2, or 3)");
			System.out.println(" 1 - service unavailable at time of visit\n"
					+ " 2 – service not present at facility\n"+""
							+ "3 - non payment.");
			 reasonCode = input.nextInt();
			 
			 CallableStatement cstmt;
				try {
					cstmt = conn.prepareCall("{CALL AddReason(?,?,?,?,?)}");
					
					cstmt.setInt(1, reasonCode);
					cstmt.setString(2, serviceName);
					cstmt.setString(3, Description);
					cstmt.setInt(4, patientId);
			    	cstmt.setInt(5, facilityId);
			    	cstmt.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
		}
		case 4:
		{   
			
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
