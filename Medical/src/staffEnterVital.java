import java.util.*;
import java.sql.*;
import java.sql.Date;

public class staffEnterVital {
	Connection conn = null;
	
	int temperature;
	int systolic;
	int diastolic;
	
	int patientId;
	int facilityId;
	String priority;
	
	staffEnterVital(Connection con, int patId, int facId){
		this.conn = con;
		System.out.println("Please enter the patient's vitals before proceeding.\n ");
		this.patientId = patId;
		this.facilityId = facId;
	}
	public void listMenu() {
		
		Scanner input = new Scanner(System.in);
		
		
		System.out.println("1. Record");
		System.out.println("2. Go back");
		
		
		System.out.println("Enter your choice to fill : ");		
		int choice = input.nextInt();
		
		
        switch(choice) {
		
		case 1:
		{   System.out.println("A. Temperature: ");
		     this.temperature = input.nextInt();
		     System.out.println("B. Systolic Blood Pressure: ");
		     this.systolic  = input.nextInt();
		     System.out.println("C. Diastolic Blood Pressure: ");
		     this.diastolic = input.nextInt();
		     priority= "normal" ;    //change this
		     
		     
		     ///////Calculate and display the priority and trigger the assessment
		     
		     try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select MAX(ASSESSMENTID) from ASSESSMENTRULES");
				int maxId = 1;
				while(rs.next()) {
					maxId = rs.getInt("MAX(ID)");
				}
				int checkinId = stmt.executeQuery("select CHECKIN_ID from LOG_IN  where PATIENT_ID = " + patientId + "and FACILITY_ID = " + facilityId ).getInt("CHECKIN_ID");
				
				for(int i = 1; i<= maxId; i++) {

					
					Boolean thisRule = true;
					rs = stmt.executeQuery("select SYMPTOMCODE, SEVERITY, PRIORITY from ASSESSMENTRULES where ASSESSMENTID = " + i);
					

					while(rs.next()) {
						
						ResultSet loop = stmt.executeQuery("select SEVERITY from SYMPTOMMETADATA where PATIENTID = " 
					+ patientId + " and SYM_CODE = " + rs.getString("SYMPTOMCODE") + "and CHECKINID = " + checkinId);
						
						String sev = rs.getString("SEVERITY");
						String patSev = loop.getString("SEVERITY");
						if(Character.isDigit(sev.charAt(0)) && Character.isDigit(patSev.charAt(0)) ) {
							int sever = Integer.parseInt(sev);
							int patSever = Integer.parseInt(patSev);
							
							if(!(patSever >= sever)) {
								thisRule = false;
								break;
							}
						}
						else if(!sev.equals(patSev)) {
							thisRule = false;
							break;
						}
					}
					if(thisRule) {
						priority = rs.getString("PRIORITY");
						break;
					}
					
					
					/////////////////SQL PROCEDURE FOR UPDATING PRIORITY in the CHECK_IN table with priority value as above
					
					System.out.println("This is the priority of the patient : " + priority);
				}
			} catch (SQLException e1) {
				
			}		     
		     

		     ///////////////////////////////////CHANGE CHANGE CHANGE ////////////////////////////////////////

		     String sql ="CALL AddVitals(?,?,?,?,?,?)"; 
		    	CallableStatement cstmt;
				try {
					cstmt = conn.prepareCall(sql);
					cstmt.setString(1, priority);
			    	cstmt.setInt(2, temperature);
			    	cstmt.setInt(3, patientId);
			    	cstmt.setInt(4, systolic);
			    	cstmt.setInt(5,diastolic);
			    	
			    	cstmt.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     System.out.println(" Back In Staff Process Patient");
		     break;
		}
		case 2:
		{   System.out.println(" Going back to Staff Process Patient");
			input.close();
			return;
		}
		default:
		{
			System.out.println("Please enter valid choice");
		} 
	
		
		
		input.close();
		
		
		}
	}
}
