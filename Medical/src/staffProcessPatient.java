import java.util.*;
import java.sql.*;
import java.sql.Date;

public class staffProcessPatient {
	Connection conn = null;
	int facilityId;
	int empId;
	int patientchoice;
	staffProcessPatient(Connection con,int facId,int empid){
		this.conn = con;
		this.facilityId=facId;
		this.empId=empid;
		System.out.println("This is the staff processing the patient page.\n ");
	}
	public void listMenu() {
	
		while(true) {
			



	    ///// need to verify whether this user is medical staff ..
			try {
				Statement stmt = conn.createStatement();
				int patId=0;
				ResultSet rs = stmt.executeQuery("SELECT Employee_Id From Medical_staff where EMPLOYEE_ID = " + empId);
				while(rs.next())
					patId = rs.getInt("Employee_Id");
					
				if(patId==0)
				{   System.out.println("Access only to medical staff");
					return;}
				
			}
			catch(SQLException e1 ){
				e1.printStackTrace();
			}

			
			///// need to display the patient list who have completed self check-in
			try {
				Statement stmt = conn.createStatement();
				
				ResultSet rs1 = stmt.executeQuery("SELECT PATIENT.PATIENT_ID as p, PATIENT.FIRSTNAME as p1, PATIENT.LASTNAME as p2 from PATIENT, PROCESS, CHECKIN,LOG_IN "
						+ "where CHECKIN.START_TIME is not null and CHECKIN.END_TIME is null "
						+ "and CHECKIN.CHECKINID = PROCESS.CHECKINID and PATIENT.PATIENT_ID = PROCESS.PATIENT_ID  AND LOG_IN.PATIENT_ID=PATIENT.PATIENT_ID AND LOG_IN.FACILITY_ID="+ facilityId);
				while(rs1.next()) {
					
					System.out.println(rs1.getInt("p") + ". " + rs1.getString("p1") + ", " + rs1.getString("p2"));
				}
				///treated patient list.....
				
			}
			catch(SQLException e1 ){
				e1.printStackTrace();
			}
			
			Scanner input = new Scanner(System.in);
			
	        System.out.println("Please enter your patient Id no: ");
			
			 patientchoice = input.nextInt();
			
			
			System.out.println("1. Enter Vitals");
			System.out.println("2. Treat Patient ");
			System.out.println("3. Go back");
			System.out.println("Enter your choice : ");
			int choice = input.nextInt();
			
			switch(choice) {
			
			case 1:
			{   
				staffEnterVital sev= new staffEnterVital(conn, patientchoice, facilityId);
				sev.listMenu();
				break;
			}
			case 2:
			{
				/// inputs : patientId, EmpId, Facility ID
				/// SQL PROCEDURE to check if staff has specialization in some body part of symptom of patient
				//if that is true move patient to treated list.
				String sql ="CALL MedicalStaffValidation(?,?,?,?)"; 
		    	CallableStatement cstmt;
		    	int ret=0;
				try {
					cstmt = conn.prepareCall(sql);
					
			    	cstmt.setInt(1, empId);
			    	cstmt.setInt(2, patientchoice);
			    	cstmt.setInt(3, facilityId);
			    
			    	cstmt.registerOutParameter(4, Types.INTEGER);
			    	
			    	
			    	cstmt.executeQuery();
			    	
			    	ret=cstmt.getInt(4);
			    	
			    	} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   //// will be done in the same procedure
			   if(ret == 1) {
				   System.out.println("Patient treated..");
			   }
			   else {
			   System.out.println("inadequate privilege");
			}
			
				break;
			}
			case 3:
			{    System.out.println("redirecting to home page");
				return;
			}
			default:
			{
				System.out.println("Please enter valid choice");
			}
			
			if(choice==3) {
				break;
			}
			}
	}
}
}
