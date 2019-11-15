import java.util.*;
import java.sql.*;

public class addAssessmentRule {
	
	Connection conn = null;
	int empId;
	int severityscale=0;
	addAssessmentRule(Connection con,int empId){
		this.empId=empId;
		System.out.println("Staff entering the assesment rule");
		this.conn = con;
	}
	
	public void listMenuOrder() throws SQLException {
		
		Map<Integer, String> symCodes = new HashMap<>();
		
		patientCheckIn pci = new patientCheckIn(conn);
		while(true) {
		Scanner input = new Scanner(System.in);
		
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT NAME,sym_code from SYMPTOMS");
		int j=0;
		while(rs.next()) {
			String sym = rs.getString("NAME");
			String symcode = rs.getString("sym_code");
			symCodes.put(j,symcode);
			j++;
			System.out.print(j);
			System.out.println(". " + sym);
		}
		
		    j++;
			System.out.println(j+ ".enter the priority");
	
		
		System.out.println("Please enter your choice no: ");
		
		int choice = input.nextInt();
	    String Scode=symCodes.get(choice) ;
	     if(choice >= 1 && choice < j-1) {
	    	 
	    	    
				ResultSet temp = stmt.executeQuery("SELECT Scale FROM SeverityScale,Symptoms WHERE Symptoms.code = " + Scode +"and Symptoms.scaleId= Severityscale.scaleId" );
				String sName = temp.getString("scale");
				System.out.println("Select a severity from "+sName);
				String severity=input.nextLine();
				//---> should display the appropriate severity scale for user to enter proper value
		}
		
	     else if(choice==j) {
	    		System.out.println("1. High");
	    		System.out.println("2. Normal");
	    		System.out.println("3. Quarantine");
	    		
	    		System.out.println("Enter your choice to fill : ");		
	    		choice = input.nextInt();
	     }
		
	
		
		switch(choice) {
		//add the assessment to assessment table
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
		
		
		
		//---> go back to staff menu
	}
}
