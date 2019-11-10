import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class SignIn {
	
	Connection conn = null;
	
	int facilityId;
	String lastName;
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
			
		   if(ispat == 'Y' || ispat == 'y') 
			   isPatient=true;
		   else
			   isPatient=false;
		  
		if(isPatient){	
			/////////////////list of facility ID's has to be displayed here..Select statement
			
		System.out.println("A. Facility ID : ");
		this.facilityId = input.nextInt();
		
		System.out.println("B.Last Name");
		this.lastName = input.nextLine();
		
		System.out.println("C.Date of Birth(yyyy-mm-dd");
		String date = input.nextLine();
		
			dob = Date.valueOf(date);
		
		
		System.out.println("D.City");
		this.city = input.nextLine();
		
		String sql ="CALL RetrieveUser(?,?,?,?,?)";
    	CallableStatement cstmt;
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, facilityId);
	    	cstmt.setString(2, lastName);
	    	cstmt.setString(3, city);
	    	cstmt.setDate(4, (Date) dob);
	    	cstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/////////////////////////////////       validate credentials    ///////////////////////////////////////
			patientRouting pr = new patientRouting(conn);
			pr.checkIn_Ack();
		
		
		}
		else{
			//staff sign in.......................
			
			staffMenu sm= new staffMenu(conn);
		}
		}
		else{
			System.out.println("Enter a valid choice");
		}
	}
	
	
}
