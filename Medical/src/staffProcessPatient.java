import java.util.*;
import java.sql.*;

public class staffProcessPatient {
	Connection conn = null;
	
	staffProcessPatient(Connection con){
		this.conn = con;
		System.out.println("This is the staff processing the patient page.\n ");
	}
	public void listMenu() {
	
		while(true) {
		System.out.println("1. Enter Vitals");
		System.out.println("2. Treat Patient ");
		System.out.println("3. Go back");
		///// need to verify whether this user is medical staff ..
		///// need to display the patient list 
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your choice : ");
		
		int choice = input.nextInt();
		input.close();
		switch(choice) {
		
		case 1:
		{   
			staffEnterVital sev= new staffEnterVital(conn);
			break;
		}
		case 2:
		{
			/// get primary departmetn and secondary dep of this user if it is same push patient to treated list
			
		else {
			System.out.println("inadequate privilege");
		}
			break;
		}
		case 3:
		{    System.out.println("redirecting to home page");
			break;
		}
		default:
		{
			System.out.println("Please enter valid choice");
		}
		}
		if(choice==3) {
			break;
		}
	}
	}
}
