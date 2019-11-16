import java.util.*;
import java.sql.*;

public class treatedPatientList {
	
	Connection conn;
	int facilityId;
	int patientChoice;
	public treatedPatientList(Connection con, int facId,int empId) {
		System.out.println("List of Treated patients\n");
		this.conn = con;
		this.facilityId = facId;
	}
	
	public void ListAllPatients() {
		
		// ---> sql statement to retrieve all treated patients and list them out
		/////// have to find only treated patient list.
		ArrayList<String> patients = new ArrayList<String>();
		
		Scanner input = new Scanner(System.in);
		
		
		try {
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT PATIENT_ID where FACILITY_ID = " + facilityId);
			while(rs.next()) {
				int patId = rs.getInt("PATIENT_ID");
				ResultSet temp = stmt.executeQuery("select FIRST_NAME, LAST_NAME from PATIENT where PATIENT_ID = " + patId);
				System.out.println(patId + ". " + temp.getString("LAST_NAME") + ", " + temp.getString("FIRST_NAME") );
				
			}
			
		}
		catch(SQLException e1 ){
			e1.printStackTrace();
		}
		
		System.out.println("Please enter your patient Id no: ");
		
		 patientChoice = input.nextInt();
		/////////Do something with this /////////
		//after staff choose what patient display the below menu
		
		System.out.println("1. Checkout");
		System.out.println("2. Go Back");
		
		System.out.println("Enter your choice to fill : ");		
		int choice = input.nextInt();
		int status = 5;
		while(status == 5) {
			switch(choice) {
			
			case 1:
			{
				patientReport pr = new patientReport(conn,patientChoice, facilityId);
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
