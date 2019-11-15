import java.util.*;
import java.sql.*;

public class patientReport {
	
	Connection conn = null;
    int patientId;
    int facilityId;
	public patientReport(Connection con,int patientId,int FacilityId) {
		this.patientId=patientId;
		this.facilityId=FacilityId;
		System.out.println("This is the Staff-Patient Report");
		this.conn = con;
	}
	
	public int displayMenu() {
		System.out.println("1. Discharge Status");
		System.out.println("2. Referral Status");
		System.out.println("3. Treatment");
		System.out.println("4. Negative Experience");
		System.out.println(" 5. Go Back");
		System.out.println("6. Submit");
		
		Scanner input = new Scanner(System.in); 
		int status = 0;
		while(status == 0) {
		
			System.out.println("Enter your choice to fill : ");		
			int choice = input.nextInt();
			
			switch(choice) {
			
			case 1:
			{
				dischargeStatus ds = new dischargeStatus(conn);
				String status1 = ds.displayMenu();
				///////// send this discharge status as sql query
				break;
			}
			case 2:
			{
				referralStatus rs = new referralStatus(conn);
				rs.displayMenu();
				break;
			}
			case 3:
			{
				//SQL statement to update(include a default value) into the treatment_given field of Experience
				break;
			}
			case 4:
			{
				negativeExperience ne = new negativeExperience(conn);
				ne.displayMenu();
				break;
			}
			case 5:
			{
				System.out.println("Redirecting back to treated patient list");
				return 5;
			}
			case 6:
			{	
				staffPatientReportConfirmation sprc = new staffPatientReportConfirmation(conn,patientId,facilityId);
				status = sprc.displayMenu();
				break;
			}
			default:
			{
				System.out.println("Please enter valid choice");
			}
			}
		}
		
		input.close();
		
		return status;
		
	}

}
