import java.util.*;
import java.sql.*;

public class staffProcessPatient {
	Connection conn = null;
	int facilityId;
	int empId;
	
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
				ResultSet rs = stmt.executeQuery("SELECT EmployeeId From Medical_staff where EMPLOYEE_ID = " + empId);
				
					patId = rs.getInt("EmployeeId");
					
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
				
				ResultSet rs = stmt.executeQuery("SELECT PATIENT_ID, FIRST_NAME, LAST_NAME from PATIENT, PROCESS, CHECKIN "
						+ "where CHECKIN.START_TIME != null and CHECKIN.END_TIME = null "
						+ "and CHECKIN.CHECKINID = PROCESS.CHECKINID and PATIENT.PATIENT_ID = PROCESS.PATIENTID ");
				while(rs.next()) {
					System.out.println(rs.getInt("PATIENT_ID") + ". " + rs.getString("LAST_NAME") + ", " + rs.getString("FIRST_NAME"));
				}
				
			}
			catch(SQLException e1 ){
				e1.printStackTrace();
			}
			
			Scanner input = new Scanner(System.in);
			
	        System.out.println("Please enter your patient Id no: ");
			
			int patientChoice = input.nextInt();
			
			
			System.out.println("1. Enter Vitals");
			System.out.println("2. Treat Patient ");
			System.out.println("3. Go back");
			System.out.println("Enter your choice : ");
			int choice = input.nextInt();
			input.close();
			switch(choice) {
			
			case 1:
			{   
				staffEnterVital sev= new staffEnterVital(conn, patientChoice);
				sev.listMenu();
				break;
			}
			case 2:
			{
				/// inputs : patientId, EmpId, Facility ID
				/// SQL PROCEDURE to check if staff has specialization in some body part of symptom of patient
			   int ret = 0;
			   if(ret == 1) {
				   
			   }
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
			
			if(choice==3) {
				break;
			}
			}
	}
}
}
