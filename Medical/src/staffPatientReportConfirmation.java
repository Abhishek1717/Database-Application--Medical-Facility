import java.util.*;
import java.sql.*;

public class staffPatientReportConfirmation {
	
	Connection conn = null;
	
	public staffPatientReportConfirmation(Connection con) {
		System.out.println("This is the Staff Patient Report Confirmation Page ");
		this.conn = con;
	}
	
	public void displayMenu() {
		
		//Display all the information to be given in the report
		//See the initial pdf for the details, when implementing
		
		System.out.println("1. Confirm");
		System.out.println("2. Go Back");
		
		Scanner input = new Scanner(System.in); 
		
		System.out.println("Enter your choice to fill : ");		
		int choice = input.nextInt();
		
		switch(choice) {
		
		case 1:
		{
			break;
		}
		case 2:
		{
			break;
		}
		default:
		{
			System.out.println("Please enter valid choice");
		}
		}
		
		input.close();
		
		
	}

}
