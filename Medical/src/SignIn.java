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
			
		   if(ispat == 'Y' || ispat == 'y') 
			   isPatient=true;
		   else
			   isPatient=false;
		  
		if(isPatient){	
			/////////////////list of facility ID's has to be displayed here..Select statement
			Statement stmt;
			try {
				stmt = conn.createStatement();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			ResultSet rs;
			try {
				rs = stmt.executeQuery("Select NAME from Medical_Facility");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			int i=0;
			List<String> fac= new ArrayList<String>();
			while(rs.next()) {
				String x;
				try {
					x = rs.getString("NAME");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(i + " " + x) ;
				fac.add(x);
				i++;
				
				
			}
			
			
			System.out.println("A. Facility ID : ");
			this.facilityId = input.nextInt();
			
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
			System.out.println("A. Facility ID : ");
			this.facilityId = input.nextInt();
			
			System.out.println("B. Employee Id");
			this.empId = input.nextInt();
			
			staffMenu sm= new staffMenu(conn, facilityId, empId);
		}
		}
		else{
			System.out.println("Enter a valid choice");

		}
	}
	
	
}
