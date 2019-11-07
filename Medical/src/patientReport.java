import java.util.*;
import java.sql.*;

public class patientReport {
	
	Connection conn = null;

	public patientReport(Connection con) {
		System.out.println("This is the Staff-Patient Report");
		this.conn = con;
	}
	
	public void displayMenu() {
		System.out.println("1. Discharge Status");
		System.out.println("2. Referral Status");
		System.out.println("3. Treatment");
		System.out.println("4. Negative Experience");
		System.out.println(" 5. Go Back");
		System.out.println("6. Submit");
		
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
		case 5:
		{
			break;
		}
		case 6:
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
