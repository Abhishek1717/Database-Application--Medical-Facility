import java.util.*;
import java.sql.*;

public class staffMenu {
	
	Connection conn = null;
	
	staffMenu(Connection con){
		this.conn = con;
		System.out.println("This is the Staff menu. \n");
	}
	
	public void displayMenu() {
		
		System.out.println("1. Checked In Patient List");
		System.out.println("2. Treated Patient List ");
		System.out.println("3. Add symptoms");
		System.out.println("4. Add Severity Scale");
		System.out.println("5. Add assessment rule");
		System.out.println("6. Go Back");
		
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
		
	}

}
