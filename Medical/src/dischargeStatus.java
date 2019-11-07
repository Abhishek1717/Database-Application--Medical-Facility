import java.util.*;
import java.sql.*;

public class dischargeStatus {
	
	Connection conn = null;
	public dischargeStatus(Connection con) {
		System.out.println("This is the discharge Status of the patient. \n");
		
		this.conn = con;
		
	}
	
	public void displayMenu() {
		
		// I think that there is no need of menu
		// Since we can just return the status and provide
		//a option of go back. that should suffice
		
		System.out.println("1. Successful Treatment");
		System.out.println("2. Deceased");
		System.out.println("3. Referred");
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
