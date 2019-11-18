import java.util.*;
import java.sql.*;

public class negativeExperience {
	int NegativeCode;
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
	
	public void displayMenu() {
		System.out.println("1. Enter the negative experience");
		
		System.out.println("2. Go Back");
		
		Scanner input = new Scanner(System.in); 
		
		System.out.println("Enter your choice to fill : ");		
		int choice = input.nextInt();
		
		switch(choice) {
		
		
		case 1:
		{    
			System.out.println("Enter 1 for Misdiagnosis");
			System.out.println(" Enter 2 for Patient acquired infection during hospital stay.");
			NegativeCode=input.nextInt();
			input.nextLine();
			System.out.println("Enter Description");
			Description=input.nextLine();
			
			/// add this to experience table...filling exp_code and exp_description
			CallableStatement cstmt;
			try {
				cstmt = conn.prepareCall("{CALL AddReason(?,?,?,?,?)}");
				
				cstmt.setInt(1, patientId);
		    	cstmt.setInt(2, facilityId);
		    	cstmt.setInt(3, NegativeCode);
		    	cstmt.setString(4, Description);
		    	cstmt.executeQuery();
		    	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		case 2:
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
