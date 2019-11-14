import java.util.*;
import java.sql.*;

public class addAssessmentRule {
	
	Connection conn = null;
	int empId;
	addAssessmentRule(Connection con,int empId){
		this.empId=empId;
		System.out.println("Staff entering the assesment rule");
		this.conn = con;
	}
	
	public void listMenuOrder() throws SQLException {
		
		ArrayList<String> symptoms = new ArrayList<String>();
		
		patientCheckIn pci = new patientCheckIn(conn);
		while(true) {
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
			System.out.println(". " + symptoms.get(i-1));
		}
		
		if(i==symptoms.size())
			System.out.println("enter the priority");
	
		
		System.out.println("Please enter your choice no: ");
		
		int choice = input.nextInt();
	
	     if(choice >= 1 && choice < symptoms.size()) {
			
			//---> should display the appropriate severity scale for user to enter proper value
		}
		
	     else if(choice==symptoms.size()) {
	    		System.out.println("1. High");
	    		System.out.println("2. Normal");
	    		System.out.println("3. Quarantine");
	    		
	    		System.out.println("Enter your choice to fill : ");		
	    		choice = input.nextInt();
	     
		
	
		
		switch(choice) {
		//add the assessement to assessment table
		case 1:
		{
			return;
		}
		case 2:
		{
			return;
		}
		case 3:
		{
			return;
		}
		default:
		{
			System.out.println("Please enter valid choice");
		}
		}
		}
		
		input.close();
		
		//---> go back to staff menu
	}
}
