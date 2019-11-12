import java.util.*;
import java.sql.*;
import java.sql.Date;

public class patientCheckIn {
	
	List<String> symptoms = new ArrayList<String>();                 ///// make this a 2d array to include symptom meta data
	Connection conn = null; 
	
	public patientCheckIn(Connection con) {
		System.out.println("This is the Patient Check-In page.");
		this.conn = con;
	}
	public void symptomMenu(int PatientID) throws SQLException { //this is being used in addAssessmentRule class
		
		//should change the function a lot to ensure functionality
		Statement stmt = conn.createStatement();
		
		Map<Integer, String> bodyCodes = new HashMap<>();
		ResultSet rs = stmt.executeQuery("SELECT SYM_CODE, NAME from SYMPTOMS");
		int i=1;
		while(rs.next()) {
			bodyCodes.put(i, rs.getString("SYM_CODE"));
			String x = rs.getString("NAME");
			System.out.print(i) ;
			System.out.println(". " + x) ;
			i++;	
		}
		
		System.out.println(i++);
		System.out.println(". Other");
		
		System.out.println(i);
		System.out.println(". Done");
		Scanner input = new Scanner(System.in);
		
		while(true) {
			System.out.println("Please enter your choice no: ");
			
			int choice = input.nextInt();
			
			if (choice == symptoms.size() + 2) {
				input.close(); 
				return;                                                     /// record time and validate and logout.........
			}
			else if(choice == symptoms.size() + 1 ){
				
				System.out.println("Enter Your Symptom");
				String new_symptom=input.nextLine();
				patientSymptomMeta psm=new patientSymptomMeta(conn, "/////////DEFAULT_CREATE//////////"); /// adjust for default cases
				int bodyPart=-1;
				psm.showAllOptions(bodyPart);
				////// send it to sql and create a new symptom code  and should be verified by staff before logging in 
			}
			else if(choice >= 1 && choice <= symptoms.size()) {
				patientSymptomMeta psm=new patientSymptomMeta(conn, bodyCodes.get(choice));
				int bodyPart=-1;
				
			
		    	CallableStatement cstmt;
				try {
					cstmt = conn.prepareCall("{CALL RetrieveBodyPart_Symptom(?,?,?,?,?,?,?,?,?)}");
					
					cstmt.setString(1, bodyCodes.get(choice));
			    	cstmt.registerOutParameter(2, bodyPart);
		
			    	cstmt.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/// if body trigger==null then pass -1 otherwise pass the body part id 
				psm.showAllOptions(bodyPart);
			}
		
		}
				
	}
}
