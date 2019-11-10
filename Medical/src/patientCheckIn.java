import java.util.*;
import java.sql.*;

public class patientCheckIn {
	
	List<String> symptoms = new ArrayList<String>();                 ///// make this a 2d array to include symptom meta data
	Connection conn = null; 
	
	public patientCheckIn(Connection con) {
		System.out.println("This is the Patient Check-In page.");
		this.conn = con;
	}
	public void symptomMenu() throws SQLException { //this is being used in addAssessmentRule class
		
		//should change the function a lot to ensure functionality
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT NAME from SYMPTOMS");   //// sql.....
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
		System.out.println(". Other");
		
		System.out.println(i);
		System.out.println(". Done");
		
		System.out.println("Please enter your choice no: ");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		
		if (choice == symptoms.size() + 2) {
			 return;                                                     /// record time and validate and logout.........
		}
		else if(choice == symptoms.size() + 1 ){
			System.out.println("Enter Your Symptom");
			String new_symptom=input.nextLine();
			
			////// swnd it to sql and create a new symptom code  and should be verified by staff before logging in 
		}
		else if(choice >= 1 && choice <= symptoms.size()) {
			patientSymptomMeta psm=new patientSymptomMeta(conn);
			/// if body trigger==null then pass 0 otherwise pass the body part id 
			
		}
		
		
	}
}
