import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class SignIn {
	
	Connection conn = null;
	
	int facilityId;
	String lastName;
	java.util.Date dob;
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
			this.isPatient = (input.nextLine() == "Y" || input.nextLine() == "y") ? true : false;
		if(isPatient){	
		System.out.println("A. Facility ID : ");
		this.facilityId = input.nextInt();
		
		System.out.println("B.Last Name");
		this.lastName = input.nextLine();
		
		System.out.println("C.Date of Birth(mm-dd-yyyy");
		String date = input.nextLine();
		SimpleDateFormat format = new SimpleDateFormat("mm-dd-yyyy");
		try {
			this.dob = (java.util.Date) format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("D.City");
		this.city = input.nextLine();
		
		
		
		/////////////////////////////////       validate credentials    ///////////////////////////////////////
			patientRouting pr = new patientRouting(conn);
			pr.checkIn_Ack();
		
		
		}
		else{
			//staff sign in.......................
		}
		}
		else{
			System.out.println("Enter a valid choice");
		}
	}
	
	
}
