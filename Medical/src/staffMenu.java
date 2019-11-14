import java.util.*;
import java.sql.*;

public class staffMenu {
	
	Connection conn = null;
	
	int empId;
	int facilityId;
	staffMenu(Connection con, int facId, int eId){
		this.conn = con;
		this.empId = eId;
		this.facilityId = facId;
		try {
			Statement stmt = conn.createStatement();
			ResultSet temp = stmt.executeQuery("SELECT STAFF_NAME FROM MEDICAL_STAFF WHERE EMPLOYEE_ID == " + empId);
			String sName = temp.getString("STAFF_NAME");
			System.out.println("This is the Staff menu of " + sName);
		} catch (SQLException e) {
			System.out.println("Incorrect Sign in");
			e.printStackTrace();
		}
	}
	
	public void displayMenu() {
		
		System.out.println("1. Checked In Patient List");
		System.out.println("2. Treated Patient List ");
		System.out.println("3. Add symptoms");
		System.out.println("4. Add Severity Scale");
		System.out.println("5. Add assessment rule");
		System.out.println("6. Go Back");
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your choice to fill : ");
		
		int choice = input.nextInt();
		while(true) {
		switch(choice) {
		
		case 1:
		{
		 staffProcessPatient spp= new staffProcessPatient(conn,facilityId,empId);
		break;
		}
		case 2:
		{   
			
			treatedPatientList tpl = new treatedPatientList(conn,facilityId,empId);
			tpl.ListAllPatients();
			break;
		}
		case 3:
		{
			addSymptoms as = new addSymptoms(conn,empId); 
			break;
		}
		case 4:
		{
			addSeverityScale  adSeverity = new addSeverityScale(conn,empId);
			break;
		}
		case 5:
		{   addAssessmentRule aar = new  addAssessmentRule(conn,empId);
			break;
		}
		case 6:
		{
			break;
		}
		default:
		{
			System.out.println("Please enter valid choice");
		}
		}
		if(choice==6)
			break;
		}
		
	}

}
