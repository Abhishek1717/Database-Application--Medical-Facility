import java.util.*;
import java.sql.*;

public class staffEnterVital {
	Connection conn = null;
	
	staffEnterVital(Connection con){
		this.conn = con;
		System.out.println("This is the staff entering the patient's vital page.\n ");
	}
	public void listMenu() {
		System.out.println("1. Enter Vitals");
		System.out.println("2. Go back");
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your choice to fill : ");		
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
		default:
		{
			System.out.println("Please enter valid choice");
		}
		}
	}
}
