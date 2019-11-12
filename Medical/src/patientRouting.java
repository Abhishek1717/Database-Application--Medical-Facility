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
	public void checkIn(int patientID,int FacilityId) {
		
		//get list of facilities
		
		int faciltiy=sc.nextInt();
	    
		//////////// sending(fac.get(facility),patientId); if the patient is already checked in that facility display error message
		
		boolean x = false; //if no error set it to true and proceed to check in
		if(x) {
			patientCheckIn pci=new patientCheckIn(conn);
			try {
				pci.symptomMenu(patientID);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
		
	

		
	
	public void checkIn_Ack(int patientID,int Facility_ID) {
		System.out.println("1-->CheckIn");
		System.out.println("2-->Check-out-Acknowledgement");
		System.out.println("3-->Go Back");
		
		int check=sc.nextInt();
		while(true){
			 if(check==3) {
		       return;
		 }
		 else if(check==1) {
			 checkIn(patientID,Facility_ID);		
			 break;
		 }
		 else if(check==2) {
			patientCheckoutAcknowledgment pcoa = new patientCheckoutAcknowledgment(conn);
			pcoa.displayMenu();
		 }
		 else {
			 System.out.println("Please enter a valid choice");
		 }
		
	}
}
	}
