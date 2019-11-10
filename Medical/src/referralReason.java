import java.util.*;
import java.sql.*;

public class referralReason {
	
	Connection conn = null;
	
	public referralReason(Connection con) {
		System.out.println("This is the referral reason page");
		
		this.conn = con;
	}
	
	public void displayMenu() {
		//Display the reason code, 
		//the name of the service 
		//and description
		
		System.out.println("1. Name of Service");
		System.out.println("2. Description");
		System.out.println("3. Record Reason");
		System.out.println("4. Go Back");
		
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
		case 3:
		{
			break;
		}
		case 4:
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
