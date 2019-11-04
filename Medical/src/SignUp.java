import java.util.*;
import java.sql.*;
import java.text.*;
public class SignUp {
    public String firstName,lastName,ad_City,ad_State,ad_Country,ad_Street,ad_flat,phone_No;
    public java.util.Date dob;
	public SignUp() {
		System.out.println("This is Sign up page");
	}
    public void complete_signup(Connection conn) {
    	Scanner sc=new Scanner(System.in);
    	System.out.println("Enter Firstname");
    	firstName=sc.nextLine();
    	System.out.println("Enter Lasttname");
    	lastName=sc.nextLine();
    	System.out.println("Enter Date of Birth");
    	String date =sc.nextLine();
    	SimpleDateFormat format = new SimpleDateFormat("mm-dd-yyyy");
    	this.dob = (java.util.Date) format.parse(date);
    	System.out.println("Enter Address" );
    	System.out.println("Enter Flat_No" );
    	ad_flat=sc.nextLine();
    	System.out.println("Enter Street Name");
    	ad_Street=sc.nextLine();
    	System.out.println("Enter City");
    	ad_City=sc.nextLine();
    	System.out.println("Enter State" );
    	ad_State=sc.nextLine();
    	System.out.println("Enter Country" );
    	ad_Country=sc.nextLine();
    	System.out.println("Enter Phone Number" );
    	phone_No=sc.nextLine();
    	
    	Statement stmt=conn.createStatement();
    	stmt.executeUpdate("INSERT INTO  " + "");
    	
    	System.out.println("Sign up successfull");
    	System.out.println("Navigating to Sign In..");
    	SignIn Sup_Sin=new SignIn();
    	Sup_Sin.signingIn();
    	
    	
    }
	
	public Boolean Proceed(Connection conn) {
		 System.out.println("1-->Sign up");
		 System.out.println("2-->Go Back");
		 Scanner sc=new Scanner(System.in);
		 int I_sup=sc.nextInt();
		 while(true){
			 if(I_sup==2) {
		 
			 return false;
		 }
		 else if(I_sup==1) {
			 complete_signup(conn);
			 break;
		 }
		 else {
			 System.out.println("Please enter a valid choice");
		 }
	}
		 return true;
	}
}
