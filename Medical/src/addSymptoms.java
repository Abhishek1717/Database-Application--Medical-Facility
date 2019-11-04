import java.util.*;
import java.sql.*;

public class addSymptoms {
	Connection conn = null;
	String symptomName;
	String symptomBpCode;
	String symptomSeverity;
	
	addSymptoms(Connection con){
		this.conn = con;
		System.out.println("This is the staff adding the symptoms page.\n ");
	}
	public void listMenu() {
		
		System.out.println("Please enter the following details in order before proceeding. /n");
		Scanner input = new Scanner(System.in);
		
		System.out.println("A. Symptom Name : ");
		this.symptomName = input.nextLine();
		System.out.println("B. Symptom Body Part : ");
		this.symptomBPCode = input.nextLine();
		System.out.println("C. Symptom Severity : ");
		this.symptomSeverity = input.nextLine();
		
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

}
