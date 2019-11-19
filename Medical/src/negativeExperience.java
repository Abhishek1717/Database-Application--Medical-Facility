import java.util.*;
import java.sql.*;

public class negativeExperience {
	String NegativeCode;
	String Description;
	int facilityId;
	int patientId;
	
	Connection conn = null;
	public negativeExperience(Connection con, int patId, int facId) {
		System.out.println("This is the negative experience page");
		
		this.conn =con;
		this.patientId = patId;
		this.facilityId = facId;
	}
	
	public List<String> displayMenu() {
		
		List<String> negs = new ArrayList<>(2);
		System.out.println("1. Enter the negative experience");
		
		System.out.println("2. Go Back");
		
		Scanner input = new Scanner(System.in); 
		
		System.out.println("Enter your choice to fill : ");		
		int choice = input.nextInt();
		input.nextLine();
		
		switch(choice) {
		
		
		case 1:
		{    
			
			System.out.println("Enter 1 for Misdiagnosis.\n "
					+ "Enter 2 for Patient acquired infection.");
			
			NegativeCode = input.nextLine();
			
			System.out.println("Enter Description");
			Description=input.nextLine();
			negs.add(NegativeCode);
			negs.add(Description);
			/// add this to experience table...filling exp_code and exp_description
			return negs;
		}
		case 2:
		{
			negs.add(null);
			negs.add(null);
			System.out.println("Redirecting back to patient Staff patient report");
			return negs;
		}
		default:
		{
			System.out.println("Please enter valid choice");
		}
		}
		
		negs.add(null);
		negs.add(null);
		return negs;
	}

}
