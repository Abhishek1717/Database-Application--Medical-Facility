import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class SignIn {
	
	int facilityId;
	String lastName;
	java.util.Date dob;
	String city;
	Boolean isPatient;
	Connection conn = null;
	public SignIn(Connection con) {
		
		System.out.println("This is the SignIn page.");
		this.conn = con;
	}
	
	public void signingIn() {
		
		System.out.println("Please enter the following details in order before proceeding to sign in./n");
		
		Scanner input = new Scanner(System.in);
		
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
		String city = input.nextLine();
		
		System.out.println("B. Are you a Patient? (Y/n)");
		Boolean isPatient = input.nextLine() == "Y" ? true : false;
		
		if (isPatient) {
			patientRouting pr = new patientRouting(conn);
		}
	}
	
	
}
