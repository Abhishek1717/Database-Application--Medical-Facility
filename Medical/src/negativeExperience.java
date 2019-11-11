import java.util.*;
import java.sql.*;

public class negativeExperience {
	
	Connection conn = null;
	public negativeExperience(Connection con) {
		System.out.println("This is the negative experience page");
		
		this.conn =con;
	}
	
	public void displayMenu() {
		System.out.println("1. Negative Experience code");
		System.out.println("2. Description");
		System.out.println("3. Save");
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
			input.close();
			return;
		}
		default:
		{
			System.out.println("Please enter valid choice");
		}
		}
		
		input.close();
	}

}
