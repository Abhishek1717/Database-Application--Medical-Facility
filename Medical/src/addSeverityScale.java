import java.util.*;
import java.sql.*;

public class addSeverityScale {
	
	//Will be added if time permits
	Connection conn = null;
	addSeverityScale(Connection con){
		System.out.println("This is staff entering a severity scale");
		this.conn = con;
	}
	
	public void displayMenu() {
		System.out.println("1.There's another level to this scale");
		System.out.println("2.There's no more levels, Go Back");
		
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
		default:
		{
			System.out.println("Please enter valid choice");
		}
		}
		
		input.close();
	}
	
}
