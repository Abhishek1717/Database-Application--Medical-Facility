import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.text.*;
public class SignUp {
    public String firstName,lastName,ad_City,ad_State,ad_Country,ad_Street,phone_No;
    public int ad_flat;
    public Date dob;
	public SignUp() {
		System.out.println("This is Sign up page");
	}
    public void complete_signup(Connection conn) throws ParseException {
    	Scanner sc=new Scanner(System.in);
    	System.out.println("Enter Firstname");
    	firstName=sc.nextLine();
    	System.out.println("Enter Lasttname");
    	lastName=sc.nextLine();
    	System.out.println("Enter Date of Birth");
    	String date =sc.nextLine();
    	//SimpleDateFormat format = new SimpleDateFormat("mm-dd-yyyy");
    	this.dob = Date.valueOf(date);
    	System.out.println("Enter Address" );
    	System.out.println("Enter Flat_No" );
    	ad_flat=sc.nextInt();
    	String s=sc.nextLine();
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
    	
    	
    	String sql = "{CALL SignUp(?,?,?,?,?,?,?,?,?)}";
    	CallableStatement cstmt;
		try {
			cstmt = conn.prepareCall("{CALL SignUp(?,?,?,?,?,?,?,?,?)}");
			
			cstmt.setString(1, firstName);
	    	cstmt.setString(2, lastName);
	    	cstmt.setDate(3, (Date) dob);
	    	cstmt.setString(4, phone_No);
	    	cstmt.setInt(5, ad_flat);
	    	cstmt.setString(6, ad_Street);
	    	cstmt.setString(7, ad_City);
	    	cstmt.setString(8, ad_State);
	    	cstmt.setString(9, ad_Country);
	    	cstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	System.out.println("Sign up successfull");
    	System.out.println("Navigating to Sign In..");
    	SignIn Sup_Sin=new SignIn(conn);
    	Sup_Sin.signingIn();
    	
    	
    }
	
	public Boolean Proceed(Connection conn) {
		 System.out.println("1-->Sign up");
		 System.out.println("2-->Go Back");
		 Scanner sc=new Scanner(System.in);
		 
		 while(true){
			 int I_sup=sc.nextInt();
			 if(I_sup==2) {
		 
			 return false;
		 }
		 else if(I_sup==1) {
			 try {
				complete_signup(conn);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 break;
		 }
		 else {
			 System.out.println("Please enter a valid choice");
		 }
	}
		 return true;
	}
}
