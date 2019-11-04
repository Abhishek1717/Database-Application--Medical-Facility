import java.util.*;
import java.sql.*;

public class staffProcessPatient {
	Connection conn = null;
	
	staffProcessPatient(Connection con){
		this.conn = con;
		System.out.println("This is the staff processing the patient page.\n ");
	}
	public void listMenu() {
		System.out.println("1. Enter Vitals");
		System.out.println("2. Treat Patient ");
		System.out.println("3. Go back");
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your choice : ");
		
		int choice = input.nextInt();
		input.close();
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
		default:
		{
			System.out.println("Please enter valid choice");
		}
		}
	}
}
