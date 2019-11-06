import java.util.*;
import java.sql.*;

public class addAssessmentRule {
	
	Connection conn = null;
	addAssessmentRule(Connection con){
		System.out.println("Staff entering the assesment rule");
		this.conn = con;
	}
	
	public void listMenuOrder() throws SQLException {
		
		ArrayList<String> symptoms = new ArrayList<String>();
		
		patientCheckIn pci = new patientCheckIn(conn);
		
		Scanner input = new Scanner(System.in);
		
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT NAME from SYMPTOMS");
		while(rs.next()) {
			String sym = rs.getString("NAME");
			symptoms.add(sym);
		}
		int i = 1;
		for(i = 1; i<= symptoms.size(); i++) {
			System.out.print(i);
			System.out.println(". " + symptoms[i-1]);
		}
		System.out.println(i++);
		System.out.println(". other");
		
		System.out.println(i);
		System.out.println(". Done");
		
		System.out.println("Please enter your choice no: ");
		
		int choice = input.nextInt();
		
		if(choice == symptoms.size() + 1 ){
			// I think not needed but review later.
		}
		else if(choice >= 1 && choice <= symptoms.size()) {
			
			//---> should display the appropriate severity scale for user to enter proper value
		}
		
		
		
		System.out.println("1. High");
		System.out.println("2. Normal");
		System.out.println("3. Quarantine");
		
		System.out.println("Enter your choice to fill : ");		
		choice = input.nextInt();
		
		switch(choice) {
		
		case 1:
		{
			break;
		}
		case 2:
		{
			break;
		}
		case 3:
		{
			break;
		}
		default:
		{
			System.out.println("Please enter valid choice");
		}
		}
		
		input.close();
		
		//---> go back to staff menu
	}
}
