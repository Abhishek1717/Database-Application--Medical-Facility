import java.util.*;
import java.sql.*;
import java.sql.Date;

public class staffEnterVital {
	Connection conn = null;
	
	int temperature;
	int systolic;
	int diastolic;
	
	int patientId;
	String priority;
	
	staffEnterVital(Connection con, int patId){
		this.conn = con;
		System.out.println("Please enter the patient's vitals before proceeding.\n ");
		this.patientId = patId;
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
				for(int i = 1; i<= maxId; i++) {
					bool thisRule = false;
					rs = stmt.executeQuery("select SYMPTOMCODE, SEVERITY from ASSESSMENTRULES where ASSESSMENTID = " + i);
					while(rs.next()) {
						ResultSet loop = stmt.executeQuery("select SEVERITY from SYMPTOMMETADATA where PATIENTID = " 
					+ patientId + " and SYM_CODE = " + rs.getString("SYMPTOMCODE") + "and CHECKINID = " + checkinid);
						if(!rs.getString("SEVERITY").equals(loop.getString("SEVERITY"))) {
							thisRule = false;
							break;
						}
					}
					if(i == maxId + 1 && thisRule) {
						priority
					}
				}
			} catch (SQLException e1) {
				
			}		     
		     
		     ///////////////////////////////////CHANGE CHANGE CHANGE ////////////////////////////////////////

		     String sql ="CALL Patient_InitialCheckin(?,?,?,?,?,?)"; 
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
