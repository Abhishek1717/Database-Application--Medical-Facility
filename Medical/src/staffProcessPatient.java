import java.util.*;
import java.sql.*;

public class staffProcessPatient {
	Connection conn = null;
	int facilityId,empid;
	
	staffProcessPatient(Connection con,int facId,int empid){
		this.conn = con;
		this.facilityId=facId;
		this.empid=empid;
		System.out.println("This is the staff processing the patient page.\n ");
	}
	public void listMenu() {
	
		while(true) {
		///// need to display the patient list 
			
	
	///// need to verify whether this user is medical staff ..
		try {
			Statement stmt = conn.createStatement();
			int patId=0;
			ResultSet rs = stmt.executeQuery("SELECT EmployeeId From Medical_staff where EMPLOYEE_ID = " + empid + "UNION SELECT deptID from SecondaryDepartment Where EmployeeID="+ empid );
			
				patId = rs.getInt("EmployeeId");
				
			if(patId==0)
			{   System.out.println("Access only to medical staff");
				return;}
			
		}
		catch(SQLException e1 ){
			e1.printStackTrace();
		}
			
		
		///// need to display the patient list who have completed self checkin
	
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("1. Enter Vitals");
		System.out.println("2. Treat Patient ");
		System.out.println("3. Go back");
		System.out.println("Enter your choice : ");
		int choice = input.nextInt();
		input.close();
		switch(choice) {
		
		case 1:
		{   
			staffEnterVital sev= new staffEnterVital(conn);
			break;
		}
		case 2:
		{
			
			try {
				Statement stmt = conn.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT Patient_ID,pa From MEDICAL_STAFF where EMPLOYEE_ID = " + empid + "UNION SELECT deptID from SecondaryDepartment Where EmployeeID="+ empid );
				while(rs.next()) {
					int patId = rs.getInt("PATIENT_ID");
					ResultSet temp = stmt.executeQuery("select FIRST_NAME, LAST_NAME from PATIENT where PATIENT_ID = " + patId);
					System.out.println(patId + ". " + temp.getString("LAST_NAME") + ", " + temp.getString("FIRST_NAME") );
					
				}
				
			}
			catch(SQLException e1 ){
				e1.printStackTrace();
			}/// get primary department and secondary dep of this user if it is same push patient to treated list
				
		   if() {}
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
