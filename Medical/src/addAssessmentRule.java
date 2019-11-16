import java.util.*;
import java.sql.*;

class symVal{
	public String symCode;
	public String seVal;
	
	public symVal(String sc, String sv) {
		this.symCode = sc;
		this.seVal = sv;
	}
}

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
		
		int curId = 0;
		List<symVal> symvals = new ArrayList<>();
		Map<Integer, String> symCodes = new HashMap<>();
		
		patientCheckIn pci = new patientCheckIn(conn);
		
		Statement stmt = conn.createStatement();
		ResultSet rid = stmt.executeQuery("SELECT MAX(ASSESSMENTID) from ASSESSMENTRULES");
		while(rid.next()) {
			curId = rid.getInt("MAX(ID)") + 1;
		}
		
		
		while(true) {
		Scanner input = new Scanner(System.in);
		
		
		
		ResultSet rs = stmt.executeQuery("SELECT NAME,SYM_CODE from SYMPTOMS");
		int j=0;
		while(rs.next()) {
			String sym = rs.getString("NAME");
			String symcode = rs.getString("SYM_CODE");
			symCodes.put(j,symcode);
			j++;
			System.out.print(j);
			System.out.println(". " + sym);
		}
		
		    j++;
			System.out.println(j+ ".enter the priority");
	
		
		System.out.println("Please enter your choice no: ");
		
		int choice = input.nextInt();
	    String Scode=symCodes.get(choice);
	    
	     if(choice >= 1 && choice < j-1) {
	    	 
	    	    
				ResultSet temp = stmt.executeQuery("SELECT Scale FROM SeverityScale,Symptoms WHERE Symptoms.code = " 
				+ Scode +"and Symptoms.scaleId= Severityscale.scaleId" );
				String sName = temp.getString("scale");
				System.out.println("Select a severity from { "+sName + " }");
				
				//---> should display the appropriate severity scale for user to enter proper value

				String severity=input.nextLine();
				symvals.add(new symVal(symCodes.get(choice), severity));
		}
		
	     else if(choice == j) {
	    		System.out.println("1. High");
	    		System.out.println("2. Normal");
	    		System.out.println("3. Quarantine");
	    		
	    		System.out.println("Enter your choice to fill : ");		
	    		choice = input.nextInt();
	    		
	    		switch(choice) {
	    		//add the assessment to assessment table
	    		case 1:
	    		{
	    			
	    			for( symVal sv: symvals) {
	    				String curSym = sv.symCode;
	    				String curVal = sv.seVal;
	    				String priority = "High";
	    				int id = curId;
	    			    ////STORED PROCEDURE TO SEND THE WHOLE DATA 
	    				////to the assessment table with the above parameters
	    				
	    				
	    			}
	    			
	    			return;
	    		}
	    		case 2:
	    		{
	    			
	    			for( symVal sv: symvals) {
	    				String curSym = sv.symCode;
	    				String curVal = sv.seVal;
	    				String priority = "Normal";
	    				int id = curId;
	    			    ////STORED PROCEDURE TO SEND THE WHOLE DATA 
	    				////to the assessment table with the above parameters
	    			}
	    			return;
	    		}
	    		case 3:
	    		{
	    			for( symVal sv: symvals) {
	    				String curSym = sv.symCode;
	    				String curVal = sv.seVal;
	    				String priority = "Quarantine";
	    				int id = curId;
	    			    ////STORED PROCEDURE TO SEND THE WHOLE DATA 
	    				////to the assessment table with the above parameters
	    				
	    				
	    			}
	    			return;
	    		}
	    		default:
	    		{
	    			System.out.println("Please enter valid choice");
	    		}
	    		}
	     }
		
	
		
		
		
		}
		
		
		//---> go back to staff menu
	}
}
