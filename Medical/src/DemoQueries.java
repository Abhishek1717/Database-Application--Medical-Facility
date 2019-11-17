import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.sql.Timestamp;





public class DemoQueries {
	int facilityId,ReferrerId;
	String reason;
	Connection conn = null;
	public DemoQueries(Connection con) {
		this.conn = con;
		System.out.println("This is the Demo Queries Page");
		
		
	}
	
	
	public void Queries() {
		
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
			
			
			ResultSet rs = stmt.executeQuery("SELECT PA.FirstName || ' ' || PA.LastName AS Name, MF.Name AS Facility, C.Start_Time AS CheckInDate, C.End_Time AS DischargeDate, E.Exp_Description AS NegativeExperience FROM Patient PA, Process P, Log_In L,Medical_Facility MF,Checkin C, Experience E WHERE PA.Patient_ID=P.Patient_ID AND PA.Patient_ID=L.Patient_ID AND L.Checkin_ID=P.CheckinID AND L.Facility_ID=MF.Facility_ID AND P.CheckinID=C.CheckinID AND E.Patient_ID=P.Patient_ID AND E.CheckinID=P.CheckinID AND E.Exp_Description IS NOT NULL");
			System.out.println("Name ||  Facility ||  CheckInDate || DischargeDate || NegativeExperience");
			while(rs.next())
				 { String name = rs.getString("Name");
				  String Facility = rs.getString("Facility");
				  Timestamp ts=rs.getTimestamp("CheckInDate");
				  Timestamp ts1=rs.getTimestamp("DischargeDate");
				  String neg=rs.getString("NegativeExperience");
				  
				  System.out.println(name +"||"+ Facility +"||" + ts+"||"+ts1+"||"+neg);
				 
				 }
			    
			break;
		}
		case 2: {
			System.out.println("Enter the start time and end time");
			Date Start;
			Date end;
			System.out.println("enter start date");
			Start=input.;
			calender cal=new calender();
					Timestamp ts=new Timestamp(System.currentTimeMillis());  ;
			System.out.println("enter finish date");
			
			ResultSet rs1 = stmt.executeQuery("SELECT  MF.Name AS Facility FROM Patient PA, Process P, Log_In L, Medical_Facility MF, Checkin C, Experience E WHERE PA.Patient_ID=P.Patient_ID AND PA.Patient_ID=L.Patient_ID AND L.Checkin_ID=P.CheckinID AND L.Facility_ID=MF.Facility_ID AND P.CheckinID=C.CheckinID AND <<TimeStamp>> >= C.Start_Time AND <<Timestamp>> <= C.End_Time AND NOT EXISTS (SELECT 1 FROM Experience E WHERE E.Patient_ID=P.Patient_ID AND E.CheckinID=P.CHeckinID AND E.Exp_Description IS NOT NULL");
			System.out.println("Medical Facilities  ");
			while(rs1.next())
				 { String name = rs1.getString("Facility");
                   System.out.println(name );
				 
				 }
			
			break;
		}
		
        case 3: {
        	
        	ResultSet rs2 = stmt.executeQuery("select referrer_id,referral_facility_id  from experience group by referrer_id\n" + 
        			"having count (referral_facility_id) = (\n" + 
        			"select max(referCount) from (\n" + 
        			"select count(referral_facility_id)  referCount from experience group by referrer_id\n" + 
        			")\n" + 
        			")");
			System.out.println("Referrer_id || Referral__facility_id ");
			while(rs2.next())
				 { int  Referrer_id= rs2.getInt("referrer_id");
				 int  referral_facility_id= rs2.getInt("referrer_facility_id");
                   System.out.println(Referrer_id + "||"+ referral_facility_id);
				 
				 }
			break;
		}
        case 4: {
        	
        	ResultSet rs3 = stmt.executeQuery("select F.name from Experience E, medical_facility F where E.facility_id = F.facility_id and exp_description is not null having count(exp_description) = ( select max(negExp) from ( select facility_id, count(exp_description) negExp from Experience group by facility_id ))");
			System.out.println("Facility_Name ");
			while(rs3.next())
				 { String  Referrer_id= rs3.getString("F.name");
				 System.out.println(Referrer_id);
				 }
			break;
		}
        case 5: {
        	ResultSet rs4 = stmt.executeQuery("select F.name from Experience E, medical_facility F where E.facility_id = F.facility_id and exp_description is not null \n" + 
        			"having count(exp_description) = (\n" + 
        			"select max(negExp) from (\n" + 
        			"select facility_id, count(exp_description) negExp\n" + 
        			"from Experience group by facility_id\n" + 
        			")\n" + 
        			")");
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
