import java.sql.*;
import java.util.*;

public class firstExample {

    static final String jdbcURL 
	= "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";

    public static void main(String[] args) {
        try {

            // Load the driver. This creates an instance of the driver
	    // and calls the registerDriver method to make Oracle Thin
	    // driver available to clients.

        	
        	
            Class.forName("oracle.jdbc.driver.OracleDriver");

	    String user = "skmuppal";	     // For example, "jsmith"
	    String passwd = "200314061";	  // Your 9 digit student ID number or password


            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
          
            
            
            try {

		// Get a connection from the first driver in the
		// DriverManager list that recognizes the URL jdbcURL

		conn = DriverManager.getConnection(jdbcURL, user, passwd);
		stmt=conn.createStatement();
		while(true) {
			System.out.println("1--->Sign In");
			System.out.println("2--->Sign Up");
			System.out.println("3--->Demo Queries");
			System.out.println("4--->Exit");
			int j;
			System.out.println("Enter your choice");
			Scanner sc=new Scanner(System.in);
			int Home=sc.nextInt();
			if(Home==1) {
				SignIn sin= new SignIn(conn);
				sin.signingIn();
				}
			else if(Home==2) {
				SignUp siup=new SignUp();
				siup.Proceed(conn);
				
			}
			else if(Home==3) {
			//	DemoQueries dm= new DemoQueries();               
			}
			else if(Home==4) {
				
				close(rs);
                close(stmt);
                close(conn);
				System.out.println("Closing Connections...");
				System.out.println("Closing Application...");
				System.exit(0);
			}
			else {
				System.out.println("Please enter a valid choice");
		 
		}
		



            }
		} finally {
                close(rs);
                close(stmt);
                close(conn);
            }
        } catch(Throwable oops) {
            oops.printStackTrace();
        }
    }

    static void close(Connection conn) {
        if(conn != null) {
            try { conn.close(); } catch(Throwable whatever) {}
        }
    }

    static void close(Statement st) {
        if(st != null) {
            try { st.close(); } catch(Throwable whatever) {}
        }
    }

    static void close(ResultSet rs) {
        if(rs != null) {
            try { rs.close(); } catch(Throwable whatever) {}
        }
    }
}

