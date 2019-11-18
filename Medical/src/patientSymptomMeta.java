import java.util.*;
import java.sql.*;

public class patientSymptomMeta {
	
	Connection conn1;
	Statement stmt;
	String bpCode="";
	int duration=0;
	boolean recurring;
	String severity="";
	String incident="";	
	int SymptomCode=0;
		
	public patientSymptomMeta(Connection con, int SymptomCode) {
		this.conn1 = con;
		this.SymptomCode=SymptomCode;
		System.out.println("Please enter the metadata for the symptom");
		
	}
	
	public void showAllOptions(Connection conn,String bodyPart, int patientID) throws SQLException {
		
		Scanner input = new Scanner(System.in);
	  System.out.println(bodyPart);
		
		  
		  try {
				stmt = conn.createStatement();
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		if(bodyPart.equals("-1")) {
			System.out.println("  Enter the  Body Part");
			
			Map<Integer, String> bodyCodes = new HashMap<>();

			ResultSet rs = stmt.executeQuery("SELECT NAME,BODYPARTCODE from BODY_PART");

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
		
		System.out.println(" Enter your Duration in days?");
		this.duration =  input.nextInt();
		input.nextLine();
		System.out.println(" Is it a reoccurence? (Y/n)");
		String option = input.nextLine();
		this.recurring = (option == "Y" || option == "y")  ? true : false;
		System.out.println(" Severity");
		
		
		// show the severity scale for this and take the suitable input as suitable
		try {
			
			ResultSet temp = stmt.executeQuery("SELECT SCALE FROM SEVERITYSCALE,SYMPTOM WHERE  SYMPTOM.SCALEID= SEVERITYSCALE.SCALEID and  SYMPTOM.ID = " + SymptomCode );
			while(temp.next()) {String sName = temp.getString("SCALE");
			System.out.println("Select a severity from "+sName);
			severity=input.nextLine();
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	       
	   
		
		System.out.println("Cause Incident");
		this.incident = input.nextLine();
		
		//// pass a SQL query along with the values
		String sql ="CALL Patient_Checkin(?,?,?,?,?,?,?)"; 
    	CallableStatement cstmt;
		try {
			cstmt = conn.prepareCall(sql);
			String x="SYM00"+SymptomCode;
			cstmt.setString(1,x);
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
		
	}
	

}
