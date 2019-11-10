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
		
		List<String> scale= new ArrayList<String>();
		while(true) {
			
		System.out.println("Enter your choice to fill : ");	
		System.out.println("1.There's another level to this scale");
		System.out.println("2.There's no more levels, Go Back");
		
		Scanner input = new Scanner(System.in); 
		
			
		int choice = input.nextInt();
		
		switch(choice) {
		
		case 1:
		{    System.out.println("Enter the level");
		     String  newscale = input.nextLine();
			 scale.add(newscale);
			break;
		}
		case 2:
		{   /// send the scale details to sql and add in table
			System.out.println("BAck to Staff Menu Page");
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
	
}
