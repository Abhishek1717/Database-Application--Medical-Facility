import java.util.*;
import java.sql.*;

public class addSymptoms {
	Connection conn = null;
	
	String symptomName;
	String symptomBpCode;
	String symptomSeverity;
	int empId;
	
	addSymptoms(Connection con,int empId){
		this.empId=empId;
		this.conn = con;
		System.out.println("This is the staff adding the symptoms page.\n ");
	}
	public void listMenu() {
		
		System.out.println("Please enter the following details in order before proceeding. /n");
		Scanner input = new Scanner(System.in);
		
		
		System.out.println("A. Symptom Name : ");
		this.symptomName = input.nextLine();
		
		//// these are optional
		
		System.out.println("B. Symptom Body Part : ");
		this.symptomBpCode = input.nextLine();
		
		/// get all the severity scales for the user to choose from 
		Statement stmt;
		try {
			stmt = conn.createStatement();
			Map<Integer, String> scales = new HashMap<>();
			ResultSet rs = stmt.executeQuery("SELECT severityscale,severityID, NAME from severity");
			
			while(rs.next()) {
				
				int x = rs.getInt("severityID");
				String y=rs.getString("severityscale");
				 scales.put(x,y);
				System.out.print(x);
				System.out.println(". ") ;
		}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			
		
		System.out.println("C. Symptom Severity : ");
		this.symptomSeverity = input.nextLine();
		
		System.out.println("1. Record");
		System.out.println("2. Go back");
		
		
		System.out.println("Enter your choice to fill : ");		
		int choice = input.nextInt();
		input.close();
		
		switch(choice) {
		
		case 1:
		{   
			////  Insert into symptom table..
			
			System.out.println("BAck to Staff Menu Page");
			break;
		}
		case 2:
		{
			System.out.println("BAck to Staff Menu Page");
			break;
		}
		default:
		{
			System.out.println("Please enter valid choice");
		}
		}
	}
}

