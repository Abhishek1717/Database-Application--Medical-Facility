import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

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
				Head=siup.Proceed(conn);
				System.out.println("Back to home");
			}
			else if(Home==3) {
				DemoQueries dm= new DemoQueries();               
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

		rs = stmt.executeQuery("SELECT name FROM MEDICAL_FACILITY");

		// Now rs contains the rows of coffees and prices from
		// the COFFEES table. To access the data, use the method
		// NEXT to access all rows in rs, one row at a time

		while (rs.next()) {
		    String s = rs.getString("name");
		    System.out.println(s);
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

