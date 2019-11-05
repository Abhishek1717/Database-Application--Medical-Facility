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
		
		System.out.println("A. Enter Vitals: ");
		this.temperature = input.nextInt();
		System.out.println("B. Systolic Blood Pressure: ");
		this.systolic  = input.nextInt();
		System.out.println("C. Diastolic Blood Pressure: ");
		this.diastolic = input.nextInt();
		
		
		System.out.println("1. Record");
		System.out.println("2. Go back");
		
		
		System.out.println("Enter your choice to fill : ");		
		int choice = input.nextInt();
		input.close();
		
		switch(choice) {
		
		case 1:
		{
			break;
		}
		case 2:
		{
			break;
		}
		default:
		{
			System.out.println("Please enter valid choice");
		}
		}
	}
}
