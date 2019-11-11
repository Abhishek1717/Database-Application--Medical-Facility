import java.util.*;
import java.sql.*;

public class patientSymptomMeta {
	
	Connection conn;
	
	String bpCode;
	int duration;
	boolean recurring;
	int severity;
	String incident;
		
	public patientSymptomMeta(Connection con, String bpCode) {
		this.conn = con;
		System.out.println("Please enter the metadata for the symptom");
		
	}
	
	public void showAllOptions(int bodyPart) throws SQLException {
		
		Scanner input = new Scanner(System.in);
		
		if(bodyPart == -1) {
			System.out.println("  Enter the  Body Part");
			Statement stmt = conn.createStatement();
			Map<Integer, String> bodyCodes = new HashMap<>();
			ResultSet rs = stmt.executeQuery("SELECT NAME from BODY_PART");
			int i=0;
			while(rs.next()) {
				bodyCodes.put(i, rs.getString("BODYPARTCODE"));
				String x = rs.getString("NAME");
				System.out.print(i) ;
				System.out.println(". " + x) ;
				// Insert into table 
				i++;	
			}
			
			System.out.println("Enter your choice: ");
			int option = input.nextInt();
			////// send bodyCodes.get(option) to update in the symptom meta data
			////// include a new attribute named bpCode
			
		}
		
		System.out.println(" Enter your Duration?");
		this.duration =  input.nextInt();
		System.out.println(" Is it a reoccurence? (Y/n)");
		char option = input.nextLine().charAt(0);
		this.recurring = (option == 'Y' || option == 'y')  ? true : false;
		System.out.println(" Severity");
		// show the severity scale for this and take the suitable input as suitable
		
		System.out.println(" Incident");
		this.incident = input.nextLine();
		
		//// pass a SQL query along with the values
		
	}
	

}
