import java.util.*;
import java.sql.*;
import java.sql.Date;

public class addSymptoms {
	Connection conn = null;
	
	String symptomName;
	String symptomBpCode;
	String symptomSeverity;
	int severityId;
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
		
		Statement stmt;
		try {
			stmt = conn.createStatement();
			Map<Integer, String> bodyCodes = new HashMap<>();
			ResultSet rs1 = stmt.executeQuery("SELECT BODYPARTCODE,NAME from BODY_PART");
			int i=0;
			while(rs1.next()) {
				bodyCodes.put(i, rs1.getString("BODYPARTCODE"));
				String x = rs1.getString("NAME");
				System.out.print(i) ;
				System.out.println(". " + x) ;
				// Insert into table the body part
				i++;}
			System.out.println("B. Enter Symptom Body Part Choice: ");
			int Choice = input.nextInt();
			symptomBpCode=bodyCodes.get(Choice);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		/// get all the severity scales for the user to choose from 
		
		try {
			stmt = conn.createStatement();
			Map<Integer, String> scales = new HashMap<>();
			ResultSet rs = stmt.executeQuery("SELECT scale,scaleID from severityscale");
			
			while(rs.next()) {
				
				int x = rs.getInt("scaleID");
				String y=rs.getString("scale");
				 scales.put(x,y);
				System.out.print(x);
				System.out.println(". ") ;
				System.out.println(y) ;
		}
			
			System.out.println("C. Please select a choice : ");
			severityId= input.nextInt();	
		
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			
		
		
		
		System.out.println("1. Record");
		System.out.println("2. Go back");
		
		
		System.out.println("Enter your choice to fill : ");		
		int choice = input.nextInt();
		input.close();
		
		switch(choice) {
		
		case 1:
		{   
			
			
			String sql ="CALL AddSymptoms(?,?,?)"; 
	    	CallableStatement cstmt;
			try {
				cstmt = conn.prepareCall(sql);
				
		    	cstmt.setString(1, symptomName);
		    	cstmt.setString(2, symptomBpCode);
		    	cstmt.setInt(3, severityId);

		    	cstmt.executeQuery();
		    	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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

