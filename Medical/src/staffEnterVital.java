import java.util.*;
import java.sql.*;
import java.sql.Date;

public class staffEnterVital {
	Connection conn = null;
	
	int temperature;
	int systolic;
	int diastolic;
	String priority;
	
	staffEnterVital(Connection con){
		this.conn = con;
		System.out.println("Please enter the patient's vitals before proceeding.\n ");
	}
	public void listMenu(int patientid) {
		
		Scanner input = new Scanner(System.in);
		
		
		System.out.println("1. Record");
		System.out.println("2. Go back");
		
		
		System.out.println("Enter your choice to fill : ");		
		int choice = input.nextInt();
		
		
        switch(choice) {
		
		case 1:
		{   System.out.println("A. Temperature: ");
		     this.temperature = input.nextInt();
		     System.out.println("B. Systolic Blood Pressure: ");
		     this.systolic  = input.nextInt();
		     System.out.println("C. Diastolic Blood Pressure: ");
		     this.diastolic = input.nextInt();
		     priority= "low" ;    //change this
		     //store the end time and displat  the priority and trigger the assessment
		     
		     String sql ="CALL Patient_InitialCheckin(?,?,?,?,?,?)"; 
		    	CallableStatement cstmt;
				try {
					cstmt = conn.prepareCall(sql);
					cstmt.setString(1, priority);
			    	cstmt.setInt(2, temperature);
			    	cstmt.setInt(3, patientid);
			    	cstmt.setDate(4, (Date) time);  // checkin start time
			    	cstmt.setInt(5, systolic);
			    	cstmt.setInt(6,diastolic);
			    	
			    	cstmt.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     System.out.println(" Back In Staff Process Patient");
		     break;
		}
		case 2:
		{   System.out.println(" Going back to Staff Process Patient");
			input.close();
			return;
		}
		default:
		{
			System.out.println("Please enter valid choice");
		} 
	
		
		
		input.close();
		
		
		}
	}
}
