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
	
	public void showAllOptions(String bodyPart, int patientID) throws SQLException {
		
		Scanner input = new Scanner(System.in);
		
		if(bodyPart.equals("-1")) {
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
			int choice = input.nextInt();
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
		String sql ="CALL Patient_Checkin(?,?,?,?,?,?,?)"; 
		symptom varchar2,
		Duration float,
		patient_id int,
		startdate Date,
		Severity varchar2,
		cause varchar2,
		FirstTimeOccurence char 
    	CallableStatement cstmt;
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setString(1,symptom);
	    	cstmt.setString(2,duration);
			cstmt.setString(3,);
	    	cstmt.setString(bodyPart);
	    	cstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		input.close();
	}
	

}
