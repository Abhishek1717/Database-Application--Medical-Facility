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
		
	}
	
	public void displayMenu() {
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet temp = stmt.executeQuery("SELECT STAFF_NAME FROM MEDICAL_STAFF WHERE EMPLOYEE_ID = " + empId);
			String sname="";
			while(temp.next())
				 sname = temp.getString("STAFF_NAME");
			System.out.println("This is the Staff menu of " + sname);
		} catch (SQLException e) {
			System.out.println("Incorrect Sign in");
			e.printStackTrace();
		}
		Scanner input = new Scanner(System.in);
		int choice;
		while(true) {
		System.out.println("1. Checked In Patient List");
		System.out.println("2. Treated Patient List ");
		System.out.println("3. Add symptoms");
		System.out.println("4. Add Severity Scale");
		System.out.println("5. Add assessment rule");
		System.out.println("6. Go Back");
		
		
		System.out.println("Enter your choice to fill : ");
		 if(!input.hasNext())
			 input.nextLine();

		choice = input.nextInt();
		
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
			as.listMenu();
			break;
		}
		case 4:
		{
			addSeverityScale  adSeverity = new addSeverityScale(conn,empId);
			adSeverity.displayMenu();
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
