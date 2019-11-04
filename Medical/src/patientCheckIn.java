import java.util.*;
import java.sql.*;

public class patientCheckIn {
	
	List<String> symptoms = new ArrayList<String>();
	Connection conn = null;
	
	public patientCheckIn(Connection con) {
		System.out.println("This is the Patient Check-In page.");
		this.conn = con;
	}
	public void symptomMenu() throws SQLException {
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
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		
		if (choice == symptoms.size() + 2) {
			
		}
		else if(choice == symptoms.size() + 1 ){
			
		}
		else if(choice >= 1 && choice <= symptoms.size()) {
			
		}
		
		
	}
	
	
	
}
