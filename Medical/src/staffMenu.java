import java.util.*;
import java.sql.*;

public class staffMenu {
	
	Connection conn = null;
	
	staffMenu(Connection con){
		this.conn = con;
		System.out.println("This is the Staff menu. \n");
	}
	
	public void displayMenu() {
		
		System.out.println("1. Checked In Patient List");
		System.out.println("2. Treated Patient List ");
		System.out.println("3. Add symptoms");
		System.out.println("4. Add Severity Scale");
		System.out.println("5. Add assessment rule");
		System.out.println("6. Go Back");
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your choice to fill : ");
		
		int choice = input.nextInt();
		while(true) {
		switch(choice) {
		
		case 1:
		{
		 staffProcessPatient spp= new staffProcessPatient(conn);
		break;
		}
		case 2:
		{   
			///////////// which class to call here.???????????
			staffcheckout patient();
			break;
		}
		case 3:
		{
			addSymptoms as = new addSymptoms(conn); 
			break;
		}
		case 4:
		{
			addSeverityScale  adSeverity=new addSeverityScale(conn);
			break;
		}
		case 5:
		{   addAssessmentRule aar= new  addAssessmentRule(conn);
			break;
		}
		case 6:
		{
			break;
		}
		default:
		{
			System.out.println("Please enter valid choice");
		}
		}
		if(choice==6)
			break;
		}
		
	}

}
