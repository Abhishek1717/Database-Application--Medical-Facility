import java.util.*;
import java.sql.*;
import java.text.*;

public class patientRouting {
	Connection conn;
	Scanner sc= new Scanner(System.in);
	public patientRouting(Connection conn1) {
		System.out.println("This is Patient routing Page");
		conn=conn1;
	}
	public void checkIn() {
		
		//get list of facilities
		Statement stmt= conn.createStatement();
		ResultSet rs=stmt.executeQuery("Select NAME from Medical_Facility");
		int i=0;
		List<String> fac= new ArrayList<String>();
		while(rs.next()) {
			String x=rs.getString("NAME");
			System.out.println(i+" "+x) ;
			fac.add(x);
			
			
		}
		System.out.println("Enter the number of the facility from  the above list  ")
		int faciltiy=sc.nextInt();
	// sending(fac.get(facility),patientId);
		boolean x;
		if(x) {
			patientCheckIn pci=new patientCheckIn(conn);
			
		}
	}
		
	
	public void checkOutAck() {
		
	}
	public void checkIn_Ack() {
		System.out.println("1-->CheckIn");
		System.out.println("2-->Check-out-Acknowledgement");
		System.out.println("3-->Go Back");
		
		int check=sc.nextInt();
		while(true){
			 if(check==3) {
		 
			 return;
		 }
		 else if(check==1) {
			 checkIn();			 break;
		 }
		 else if(check==2) {
			checkOutAck();		 }
		 else {
			 System.out.println("Please enter a valid choice");
		 }
		
	}
}
