import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.time.*;
public class patientCheckIn {
	
	List<String> symptoms = new ArrayList<String>();                 ///// make this a 2d array to include symptom meta data
	Connection conn = null; 
	String bodyPart= "-1";
	
	public patientCheckIn(Connection con) {
		System.out.println("This is the Patient Check-In page.");
		this.conn = con;
	}
	public void symptomMenu(int PatientID) throws SQLException { //this is being used in addAssessmentRule class
		
		//should change the function a lot to ensure functionality
		Statement stmt = conn.createStatement();
		
		Map<Integer, Integer> symCodes = new HashMap<>();
		ResultSet rs = stmt.executeQuery("SELECT ID, NAME from SYMPTOM");
		int i=1;
		while(rs.next()) {
			symCodes.put(i, rs.getInt("ID"));
			String x = rs.getString("NAME");
			symptoms.add(x);
			System.out.print(i) ;
			System.out.println(". " + x) ;
			i++;	
		}
		
		System.out.print(i);
		System.out.println(". Done");
		Scanner input = new Scanner(System.in);
		
		while(true) {
			System.out.println("Please enter your choice no: ");
			
			int choice = input.nextInt();
			
			if (choice == symptoms.size() + 1) {
				////  /// rcall procedure checkin.patient_intialcheckin
				///// check this
				firstExample.main(null);
				return;                                                  
			}
			/*else if(choice == symptoms.size() + 1 ){
				
				System.out.println("Enter Your Symptom");
				String new_symptom=input.nextLine();
				patientSymptomMeta psm=new patientSymptomMeta(conn, ); /// adjust for default cases
				String bodyPart= "-1";
				psm.showAllOptions(bodyPart,PatientID);
				////// send it to sql and create a new symptom code  and should be verified by staff before logging in 
			}*/
			else if(choice >= 1 && choice <= symptoms.size()) {

				patientSymptomMeta psm=new patientSymptomMeta(conn, symCodes.get(choice));
				
				/// if body trigger==null then pass -1 otherwise pass the body part id
				String sql ="CALL RetrieveBodyPart_Symptom(?,?)"; 
		    	CallableStatement cstmt;
				try {
					String x= "SYM00"+symCodes.get(choice);
					cstmt = conn.prepareCall(sql);
					cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
					cstmt.setString(1, x);
					
			    	cstmt.executeQuery();
			    	bodyPart=cstmt.getString(2);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


				psm.showAllOptions(conn,bodyPart, PatientID);
			}
	
		}
				
	}
}
