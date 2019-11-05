import java.util.*;
import java.sql.*;

public class patientSymptomMeta {
	
	Connection conn;
	
	String bpCode;
	int duration;
	boolean recurring;
	int severity;
	String incident;
		
	public patientSymptomMeta(Connection con) {
		this.conn = con;
		System.out.println("Please enter the metadata for the symptom");
		
	}
	
	public void showAllOptions() throws SQLException {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("1. Body Part");
		System.out.println("2. Duration");
		System.out.println("3. Is it a reoccurence? (Y/n)");
		System.out.println("4. Severity");
		System.out.println("5. Incident");
		
		System.out.println("Enter your choice to fill : ");
		int choice = input.nextInt();
		input.close();
		switch(choice) {
		
		case 1:
		{Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT NAME from BODY_PART");
		
		break;
		}
		case 2:
		{
			break;
		}
		case 3:
		{
			break;
		}
		case 4:
		{
			break;
		}
		case 5:
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
