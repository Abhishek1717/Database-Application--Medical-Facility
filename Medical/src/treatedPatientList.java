import java.util.*;
import java.sql.*;

public class treatedPatientList {
	
	Connection conn;
	
	public treatedPatientList(Connection con) {
		System.out.println("List of Treated patients\n");
		this.conn = con;
	}
	
	public void ListAllPatients() {
		
		// ---> sql statement to retrieve all treated patients and list them out
		
		ArrayList<String> patients = new ArrayList<String>();
		
		Scanner input = new Scanner(System.in);
		
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("REPLACE WITH APPROPRIATE CORRECT QUERY");
		while(rs.next()) {
			String sym = rs.getString("NAME"); // nothing is correct here
			patients.add(sym);
		}
		int i = 1;
		for(i = 1; i<= patients.size(); i++) {
			System.out.print(i);
			System.out.println(". " + patients[i-1]);
		}
		
		System.out.println("Please enter "
				+ "your choice no: ");
		
		int choice = input.nextInt();
		
		if(choice >= 1 && choice <= patients.size()) {
			
			//---> should display the appropriate severity scale for user to enter proper value
		}
		
		//after staff choose what patient display the below menu
		
		System.out.println("1. Checkout");
		System.out.println("2. Go Back");
		
		System.out.println("Enter your choice to fill : ");		
		choice = input.nextInt();
		int status = 5;
		while(status == 5) {
			switch(choice) {
			
			case 1:
			{
				patientReport pr = new patientReport(conn);
				status = pr.displayMenu();
				break;
			}
			case 2:
			{
				status = 1;
				break;
			}
			default:
			{
				System.out.println("Please enter valid choice");
			}
			}
		}
		if(status == 1) {
			System.out.println("Redirecting back to staff menu");
		}
	}

}
