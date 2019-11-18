import java.util.*;
import java.sql.*;
import java.sql.Date;

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
		Map<Integer, Integer> symCodes = new HashMap<>();
		
		
		
		Statement stmt = conn.createStatement();
		ResultSet rid = stmt.executeQuery("SELECT MAX(ASSESSMENTID) as p from ASSESSMENTRULES");
		while(rid.next()) {
			curId = rid.getInt("p") + 1;
		}
		if(curId==0)
			curId+=1;
		
		
		
		while(true) {
		Scanner input = new Scanner(System.in);
		
		
		
		ResultSet rs = stmt.executeQuery("SELECT NAME,ID from SYMPTOM");
		int j=0;
		while(rs.next()) {
			String sym = rs.getString("NAME");
			int symcode = rs.getInt("ID");
			symCodes.put(j,symcode);
			j++;
			System.out.print(j);
			System.out.println(". " + sym);
		}
		
		    j++;
			System.out.println(j+ ".enter the priority");
	
		
		System.out.println("Please enter your choice no: ");
		
		int choice = input.nextInt();
		input.nextLine();
	   
	    
	     if(choice >= 1 && choice < j-1) {
	    	 
	    	 int Scode=symCodes.get(choice);
				ResultSet temp = stmt.executeQuery("SELECT Scale FROM SeverityScale,Symptom WHERE Symptom.id = " 
				+ Scode +"and Symptom.scaleId= Severityscale.scaleId" );
				while(temp.next()){
				String sName = temp.getString("scale");
				System.out.println("Select a severity from { "+sName + " }");
				}
				//---> should display the appropriate severity scale for user to enter proper value

				String severity=input.nextLine();
				symvals.add(new symVal("SYM00"+symCodes.get(choice), severity));
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
	    				String sql ="CALL AddAssessmentRule(?,?,?,?)"; 
	    		    	CallableStatement cstmt;
	    				try {
	    					cstmt = conn.prepareCall(sql);
	    					
	    			    	cstmt.setInt(1, curId);
	    			    	cstmt.setString(2, curSym);
	    			    	cstmt.setString(3, curVal);
	    			    	cstmt.setString(4, priority);
	    			    	
	    			    	
	    			    	
	    			    	cstmt.executeQuery();
	    			  
	    			    	} catch (SQLException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    				
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
	    				String sql ="CALL AddAssessmentRule(?,?,?,?)"; 
	    		    	CallableStatement cstmt;
	    				try {
	    					cstmt = conn.prepareCall(sql);
	    					
	    			    	cstmt.setInt(1, curId);
	    			    	cstmt.setString(2, curSym);
	    			    	cstmt.setString(3, curVal);
	    			    	cstmt.setString(4, priority);
	    			    	
	    			    	
	    			    	
	    			    	cstmt.executeQuery();
	    			  
	    			    	} catch (SQLException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
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
	    				String sql ="CALL AddAssessmentRule(?,?,?,?)"; 
	    		    	CallableStatement cstmt;
	    				try {
	    					cstmt = conn.prepareCall(sql);
	    					
	    			    	cstmt.setInt(1, curId);
	    			    	cstmt.setString(2, curSym);
	    			    	cstmt.setString(3, curVal);
	    			    	cstmt.setString(4, priority);
	    			    	
	    			    	
	    			    	
	    			    	cstmt.executeQuery();
	    			  
	    			    	} catch (SQLException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    				
	    				
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
