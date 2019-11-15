import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.text.*;

public class patientRouting {
	Connection conn;
	Scanner sc= new Scanner(System.in);
	public patientRouting(Connection conn1) {
		System.out.println("This is Patient routing Page");
		conn=conn1;
	}
	public void checkIn(int patientID,int FacilityId) {
		
	
		
	
	    
	
			int x = -1; //if no error set it to true and proceed to check in
			String sql ="CALL Check_LoginSession(?,?,?)"; 
	    	CallableStatement cstmt;
			try {
				cstmt = conn.prepareCall(sql);
			
		    	cstmt.setInt(1, patientID);
		    	cstmt.setInt(2, FacilityId);
		    	
		    	cstmt.registerOutParameter(3, Types.INTEGER);
		    	
		    	
		    	cstmt.executeQuery();
		    	
		    	x=cstmt.getInt(3);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		if(x==0) {
			patientCheckIn pci=new patientCheckIn(conn);
			try {
				pci.symptomMenu(patientID);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
		
	

		
	
	public void checkIn_Ack(int patientID) {
		Statement stmt;
		HashMap<Integer,String> fac= new HashMap<Integer,String>();
		ResultSet rs;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("Select NAME, FACILITY_ID from MEDICAL_FACILITY");
			int i=0;
			
			while(rs.next()) {
				String x;
				x = rs.getString("NAME");
				int y=rs.getInt("FACILITY_ID");
				fac.put(y,x);
				System.out.println(y + " " + x) ;
				i++;
			}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		int choice=0;
		while(true){
			
			System.out.println("Enter  the facilityID  you want to checkin to / checkout from");
			choice=sc.nextInt();
			System.out.println("Check 1");
	    if(fac.containsKey(choice))
	    {   break;
	    	}
	    else {
	    	System.out.println("Enter  correct Facility id");
	    }
	    
	    }
		int FacilityId=choice;
		System.out.println("1-->CheckIn");
		System.out.println("2-->Check-out-Acknowledgement");
		System.out.println("3-->Go Back");
		
		int check=sc.nextInt();
		while(true){
			 if(check==3) {
		       return;
		 }
		 else if(check==1) {
			 checkIn(patientID,FacilityId);		
			 return;
		 }
		 else if(check==2) {
			patientCheckoutAcknowledgment pcoa = new patientCheckoutAcknowledgment(conn, patientID, FacilityId);
			// 
			pcoa.displayMenu();
			return;
		 }
		 else {
			 System.out.println("Please enter a valid choice");
		 }
		
	}
}
	}
