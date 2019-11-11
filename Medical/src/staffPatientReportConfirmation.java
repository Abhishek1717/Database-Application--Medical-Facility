import java.util.*;
import java.sql.*;

public class staffPatientReportConfirmation {
	
	Connection conn = null;
	
	public staffPatientReportConfirmation(Connection con) {
		System.out.println("This is the Staff Patient Report Confirmation Page ");
		this.conn = con;
	}
	
	public int displayMenu() {
		
		//Display all the information to be given in the report
		//See the initial pdf for the details, when implementing
		
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
