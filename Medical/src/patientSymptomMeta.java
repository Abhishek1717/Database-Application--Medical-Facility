import java.util.*;
import java.sql.*;

public class patientSymptomMeta {
	
	Connection conn;
	
	String bpCode="";
	int duration=0;
	boolean recurring;
	String severity="";
	String incident="";
	String SymptomCode="";
		
	public patientSymptomMeta(Connection con, String SymptomCode) {
		this.conn = con;
		this.SymptomCode=SymptomCode;
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
				// Insert into table the body part
				i++;	
			}
			
			System.out.println("Enter your choice: ");
			int choice = input.nextInt();
			
			
			    bpCode=bodyCodes.get(choice) ;
			    
			////// send bodyCodes.get(option) to update in the symptom meta data
			////// include a new attribute named bpCode
			
		}
		
		System.out.println(" Enter your Duration?");
		this.duration =  input.nextInt();
		System.out.println(" Is it a reoccurence? (Y/n)");
		String option = input.nextLine();
		this.recurring = (option == "Y" || option == "y")  ? true : false;
		System.out.println(" Severity");
		
		
		// show the severity scale for this and take the suitable input as suitable
		try {
			Statement stmt = conn.createStatement();
			ResultSet temp = stmt.executeQuery("SELECT Scale FROM SeverityScale,Symptoms WHERE Symptoms.code = " + SymptomCode +"and Symptoms.scaleId= Severityscale.scaleId" );
			String sName = temp.getString("scale");
			System.out.println("Select a severity from "+sName);
			severity=input.nextLine();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	       
	   
		
		System.out.println("Cause Incident");
		this.incident = input.nextLine();
		
		//// pass a SQL query along with the values
		String sql ="CALL Patient_Checkin(?,?,?,?,?,?,?,?)"; 
    	CallableStatement cstmt;
		try {
			cstmt = conn.prepareCall(sql);
			
			cstmt.setString(1,SymptomCode);
	    	cstmt.setInt(2,duration);
			cstmt.setInt(3,patientID);
			cstmt.setString(4,severity);
			cstmt.setString(5,incident);
	    	cstmt.setString(6,option);
	    	cstmt.setString(7,bpCode);
	    	cstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		input.close();
	}
	

}
