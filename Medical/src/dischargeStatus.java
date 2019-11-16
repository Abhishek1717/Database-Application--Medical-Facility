import java.util.*;
import java.sql.*;

public class dischargeStatus {
	
	Connection conn = null;
	public dischargeStatus(Connection con) {
		System.out.println("This is the discharge Status of the patient. \n");
		
		this.conn = con;
		
	}
	
	public String displayMenu() {
		
		// I think that there is no need of menu
		// Since we can just return the status and provide
		//a option of go back. that should suffice
		
		System.out.println("1. Successful Treatment");
		System.out.println("2. Deceased");
		System.out.println("3. Referred");
		System.out.println("4. Go Back");
		
		Scanner input = new Scanner(System.in); 
		String discharge="";
		while(true) {
			System.out.println("Enter your choice to fill : ");		
			int choice = input.nextInt();
			
			switch(choice) {
			
			case 1:
			{
				return "Successful Treatment";
			}
			case 2:
			{
				return "Deceased";
			}
			case 3:
			{
				return "Referred";
			}
			case 4:
			{
			    return discharge;
			  
			}
			default:
			{
				System.out.println("Please enter valid choice");
			}
			}
		}
		
	}

}
