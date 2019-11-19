import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;





public class DemoQueries {
	int facilityId,ReferrerId;
	String reason;
	Connection conn = null;
	public DemoQueries(Connection con) {
		this.conn = con;
		System.out.println("This is the Demo Queries Page");
		
		
	}
	
	
	public void Queries() throws SQLException {
		
		System.out.println("1-->Find all patients that were discharged but had negative experiences at any facility, list their names" + 
				"facility, check-in date, discharge date and negative experiences");
		System.out.println("2-->Find facilities that did not have a negative experience for a specific period (to be given)");
		System.out.println("3-->For each facility, find the facility that is sends the most referrals to");
		System.out.println("4-->Find facilities that had no negative experience for patients with cardiac symptoms");
		System.out.println("5-->Find the facility with the most number of negative experiences (overall i.e. of either kind)");
		System.out.println("6-->Find each facility, list the patient encounters with the top five longest check-in phases (i.e. time from\n" + 
				"begin check-in to when treatment phase begins (list the name of patient, date, facility, duration and\n" + 
				"list of symptoms).");
		System.out.println("7-->Exit");
		Scanner input=new Scanner(System.in);
		
		Statement stmt = conn.createStatement();
		
		while(true) {
		System.out.println("Which Query you want to execute");
		int choice=input.nextInt();
		switch(choice) {
		case 1: {
			
			
			ResultSet rs;
			try {
				rs = stmt.executeQuery("SELECT PA.FirstName || ' ' || PA.LastName AS Name, MF.Name AS Facility, C.Start_Time AS CheckInDate, C.End_Time AS DischargeDate, E.Exp_Description AS NegativeExperience " + 
						" FROM Patient PA, Process P, Log_In L,Medical_Facility MF,Checkin C, Experience E" + 
						" WHERE PA.Patient_ID=P.Patient_ID AND PA.Patient_ID=L.Patient_ID AND L.Checkin_ID=P.CheckinID AND L.Facility_ID=MF.Facility_ID " + 
						" AND P.CheckinID=C.CheckinID AND E.Patient_ID=P.Patient_ID AND E.CheckinID=P.CheckinID " + 
						" AND E.Exp_Description IS NOT NULL AND C.End_Time IS NOT NULL;" );
				
				System.out.println("Name ||  Facility ||  CheckInDate || DischargeDate || NegativeExperience");
				while(rs.next())
					 { String name = rs.getString("Name");
					  String Facility = rs.getString("Facility");
					  Timestamp ts=rs.getTimestamp("CheckInDate");
					  Timestamp ts1=rs.getTimestamp("DischargeDate");
					  String neg=rs.getString("NegativeExperience");
					  
					  System.out.println(name +"||"+ Facility +"||" + ts+"||"+ts1+"||"+neg);
					 
					 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			    
			break;
		}
		case 2: {
			System.out.println("Enter the start time and end time");
			/*Date Start;
			Date end;
			System.out.println("enter start date");
			Start=input.;
			calender cal=new calender();
			
					Timestamp ts=new Timestamp(System.currentTimeMillis());  ;
			System.out.println("enter finish date");
			*/
			
			System.out.print("Enter the start date and time (YYYY/MM/DD HH:MM:SS) ");
			 String time = input.nextLine();
			 System.out.println();
			 System.out.print("Enter the finish date and time (YYYY/MM/DD HH:MM:SS): ");
			 String time2 = input.nextLine();
			 System.out.println();
			 SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/DD HH:MM:SS");
			 try {
				Date start = sdf.parse(time);
				Date end = sdf.parse(time2);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ResultSet rs1;
			try {
				rs1 = stmt.executeQuery("SELECT  MF.Facility_ID AS Facility " + 
						" FROM Patient PA, Process P, Log_In L, Medical_Facility MF, Checkin C, Experience E " + 
						" WHERE PA.Patient_ID=P.Patient_ID AND PA.Patient_ID=L.Patient_ID AND L.Checkin_ID=P.CheckinID AND L.Facility_ID=MF.Facility_ID " + 
						" AND P.CheckinID=C.CheckinID AND TO_TIMESTAMP('2003/12/13 10:13:18', 'YYYY/MM/DD HH:MI:SS') >= C.Start_Time AND TO_TIMESTAMP('2019/12/13 10:13:18', 'YYYY/MM/DD HH:MI:SS') <= C.End_Time" + 
						" AND EXISTS ( SELECT 1 FROM Experience E WHERE E.Patient_ID=P.Patient_ID AND E.CheckinID=P.CheckinID" + 
						" AND E.Exp_Description IS NOT NULL));");
				while(rs1.next())
				 { String name = rs1.getString("Facility");
                  System.out.println(name);
				 
				 }
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Medical Facilities  ");
			
			
			break;
		}
		
        case 3: {
        	
        	ResultSet rs2 = stmt.executeQuery("select L.Facility_ID,referral_facility_id  from experience E, Log_in L where E.Patient_ID = L.Patient_ID AND E.CheckinID = L.Checkin_ID" + 
        			"group by facility_id,referral_facility_id " + 
        			"having count (referral_facility_id) = (select max(referCount) from (select count(referral_facility_id)  "
        			+ "referCount from experience group by referrer_id));" );
			System.out.println("Referrer_id || Referral__facility_id ");
			while(rs2.next())
				 { int  Referrer_id= rs2.getInt("referrer_id");
				 int  referral_facility_id= rs2.getInt("referrer_facility_id");
                   System.out.println(Referrer_id + "||"+ referral_facility_id);
				 
				 }
			break;
		}
        case 4: {
        	
        	ResultSet rs3 = stmt.executeQuery("select F.name from experience E, medical_facility F,Log_in L where E.patient_id = L.Patient_ID AND E.CheckinID = L.Checkin_ID AND L.Facility_ID = F.facility_id " + 
        			"and exp_description is null and E.patient_id in( " + 
        			"select patient_id from symptommetadata where sym_code IN(select sym_code from symptoms where name='Pain')" + 
        			" and body_part_code in(select body_part_code from body_part where name in('heart')))");
			System.out.println("Facility_Name ");
			while(rs3.next())
				 { String  Referrer_id= rs3.getString("F.name");
				 System.out.println(Referrer_id);
				 }
			break;
		}
        case 5: {
        	ResultSet rs4 = stmt.executeQuery("select F.name from Experience E, medical_facility F, Log_in L where E.patient_id = L.Patient_ID "
        			+ "AND E.CheckinID = L.Checkin_ID AND L.Facility_ID = F.facility_id and exp_description is not null " + 
        			"group by exp_description,F.name having count(exp_description) = ( " + 
        			"select max(negExp) from (select referral_facility_id, count(exp_description) negExp" + 
        			"from Experience group by referral_facility_id))");
			System.out.println("Facility_Name ");
			while(rs4.next())
				 { String  Facility_Name= rs4.getString("F.name");
				 System.out.println(Facility_Name);
				 }
			break;
		}
        case 6: {
        	
			break;
		}
        case 7: {
        	System.out.println("returning to home page");
			return;
		}
        default: {
        	System.out.println("Enter valid option");
			break;
		}
	    }
		
		}
	
}
	}
