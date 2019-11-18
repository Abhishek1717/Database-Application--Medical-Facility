import java.util.*;
import java.sql.*;

public class negativeExperience {
	int NegativeCode;
	String Description;
	Connection conn = null;
	public negativeExperience(Connection con) {
		System.out.println("This is the negative experience page");
		
		this.conn =con;
	}
	
	public void displayMenu() {
		System.out.println("1. Enter the negative experience");
		
		System.out.println("2. Go Back");
		
		Scanner input = new Scanner(System.in); 
		
		System.out.println("Enter your choice to fill : ");		
		int choice = input.nextInt();
		
		switch(choice) {
		
		
		case 1:
		{    
			System.out.println("Enter 1-Misdiagnosis");
			System.out.println(" Enter 2-Patient acquired infection during hospital stay.");
			NegativeCode=input.nextInt();
			System.out.println("Enter Description");
			Description=input.nextLine();
			
			/// add this to experience table...filling exp_code and exp_description
			return;
		}
		case 2:
		{
			System.out.println("Redirecting back to patient Staff patient report");
			return;
		}
		default:
		{
			System.out.println("Please enter valid choice");
		}
		}
		
		
	}

}
