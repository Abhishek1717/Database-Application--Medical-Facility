import java.util.*;
import java.sql.*;

public class staffEnterVital {
	Connection conn = null;
	
	int temperature;
	int systolic;
	int diastolic;
	
	staffEnterVital(Connection con){
		this.conn = con;
		System.out.println("Please enter the patient's vitals before proceeding.\n ");
	}
	public void listMenu() {
		
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
		     
		     //store the end time and displat  the priority and trigger the assessment
		     System.out.println(" In Staff Process Patient");
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
