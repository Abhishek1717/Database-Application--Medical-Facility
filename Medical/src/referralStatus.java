import java.util.*;
import java.sql.*;

public class referralStatus {
	
	Connection conn = null;
	public referralStatus(Connection con) {
		System.out.println("This is the referral status page");
		
		this.conn = con;
	}
	
	public void displayMenu() {
		
		System.out.println("1. Facility ID");
		System.out.println("2. Referrer ID");
		System.out.println("3. Add reason");
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
