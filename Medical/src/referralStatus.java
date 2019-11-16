import java.util.*;
import java.sql.*;

public class referralStatus {
	int facilityId = -1;
	int ReferrerId = -1;
	String reason;
	Connection conn = null;
	public referralStatus(Connection con) {
		System.out.println("This is the referral status page");
		
		this.conn = con;
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
		
		switch(choice) {
		
		case 1:
		{
			System.out.println("Enter Facility ID");
			facilityId=input.nextInt();
			
			break;
		}
		case 2:
		{   
			System.out.println("Enter Referrer ID");
			ReferrerId=input.nextInt();
			break;
		}
		case 3:
		{   
			referralReason rr= new referralReason(conn);
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
