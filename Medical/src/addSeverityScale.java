import java.util.*;
import java.sql.*;

public class addSeverityScale {
	
	//Will be added if time permits
	Connection conn = null;
	int facilityId;
	addSeverityScale(Connection con,int fac_id){
		this.facilityId=fac_id;
		System.out.println("This is staff entering a severity scale");
		this.conn = con;
	}
	
	public void displayMenu() {
		
		String newscale="";
		while(true) {
			
		System.out.println("Enter your choice to fill : ");	
		System.out.println("1.There's another level to this scale");
		System.out.println("2.There's no more levels, Go Back");
		
		Scanner input = new Scanner(System.in); 
		
			
		int choice = input.nextInt();
		
		switch(choice) {
		
		case 1:
		{    System.out.println("Enter the level");
		       
		       newscale+=input.nextLine();
		       newscale += ",";
			
			break;
		}
		case 2:
		{   

			String sql ="CALL AddSeverityScale(?)"; 
	    	CallableStatement cstmt;
			try {
				cstmt = conn.prepareCall(sql);
		    	cstmt.setString(1, newscale);

		    	cstmt.executeQuery();
		    	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			System.out.println("Back to Staff Menu Page");
			input.close();
			return;
		}
		default:
		{
			System.out.println("Please enter valid choice");
		}
		}
		
		input.close();
		}
	}
	
}
