import java.util.*;
import java.sql.*;

public class referralReason {
	
	Connection conn = null;
	String serviceName,Description,Reason;
	
	public referralReason(Connection con) {
		System.out.println("This is the referral reason page");
		
		this.conn = con;
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
			System.out.println(" Enter reason");
			 Reason=input.nextLine();
			 i++;
			break;
		}
		case 4:
		{   
			/// call procedure to insert into reasons table.
			///// using serviceName, Description, reason parameters
			
			
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
