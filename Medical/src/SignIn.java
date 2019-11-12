import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class SignIn {
	
	Connection conn = null;
	
	int facilityId;
	String lastName;
	int empId;
	Date dob;
	String city;
	Boolean isPatient;
	
	public SignIn(Connection con) {
		
		System.out.println("This is the SignIn page.");
		this.conn = con;
	}
	
	public void signingIn() {
		
		System.out.println("Please enter the following details in order before proceeding to sign in./n");
		 System.out.println("1-->Sign In");
		 System.out.println("2-->Go Back");
		 
		Scanner input = new Scanner(System.in);             /// staff and patient login verification
		int proceed=input.nextInt();
		
		if(proceed==2){
			System.out.println("Back to home page");
			return;
		}
		else if(proceed==1)
		{
			System.out.println("B. Are you a Patient? (Y/n)");
			Character ispat=input.next().charAt(0);
			input.nextLine();
		   if(ispat == 'Y' || ispat == 'y') 
			   isPatient=true;
		   else
			   isPatient=false;
		  
		   
		
		if(isPatient){	
			
			
			
			
		
			
			System.out.println("B.Last Name");
			this.lastName = input.nextLine();
			
			System.out.println("C.Date of Birth(yyyy-mm-dd");
			String date = input.nextLine();
			
				dob = Date.valueOf(date);
			
			
			System.out.println("D.City");
			this.city = input.nextLine();
			int patientID = -1;
			/// ******Should return patient on successful insertion(STORED PROCEDURE) ////
			String sql ="CALL RetrieveUser(?,?,?,?,?)"; 
	    	CallableStatement cstmt;
			try {
				cstmt = conn.prepareCall(sql);
				
		    	cstmt.setString(1, lastName);
		    	cstmt.setString(2, city);
		    	cstmt.setDate(3, (Date) dob);
		    	cstmt.setString(4, "Y");
		    	cstmt.registerOutParameter(5, Types.INTEGER);
		    	
		    	
		    	cstmt.executeQuery();
		    	
		    	patientID=cstmt.getInt(5);
		    	System.out.println(patientID);
		    	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/////////////////////////////////       validate credentials    ///////////////////////////////////////
				if(patientID!=-1)
					{patientRouting pr = new patientRouting(conn);
			  pr.checkIn_Ack(patientID,facilityId);}
			
			
		}
		else{
			//staff sign in.......................
			
			
			System.out.println("B.Last Name");
			this.lastName = input.nextLine();
			
			System.out.println("C.Date of Birth(yyyy-mm-dd");
			String date = input.nextLine();
			
				dob = Date.valueOf(date);
			
			
			System.out.println("D.City");
			this.city = input.nextLine();
			
			/// Should return patient on successful insertion(STORED PROCEDURE) ////
			String sql ="CALL RetrieveUser(?,?,?,?,?)"; 
	    	CallableStatement cstmt;
			try {
				cstmt = conn.prepareCall(sql);
			
		    	cstmt.setString(1, lastName);
		    	cstmt.setString(2, city);
		    	cstmt.setDate(3, (Date) dob);
		    	cstmt.setString(4,"N");
		    	cstmt.registerOutParameter(5, Types.INTEGER);
		    	
		    	
		    	cstmt.executeQuery();
		    	
		    	empId=cstmt.getInt(5);
		    	System.out.println("Staff");
		    	System.out.println(empId);
		    	if(empId!=-1);
		    	//staffMenu sm= new staffMenu(conn, empId, facid);
		    	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			////////////  SQL Stored Procedure to validate if staff credentials exist ///////////////
			//if(/* the validation is true*/) {
				
			}
		}
		
		else{
			System.out.println("Enter a valid choice");

		}
	}
	
	
}
