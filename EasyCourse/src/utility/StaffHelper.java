package utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Teachingstaff;
import database.DBUtility;

public class StaffHelper {
	
	public StaffHelper(){}
	
	public List<Teachingstaff> get_staff_list()
	{
		List<Teachingstaff> ts_list = new LinkedList<Teachingstaff>();
		
		DBUtility db = new DBUtility();
		String sql = "select * from TeachingStaffs";
		ResultSet r;
		
		db.open();
		
		r = db.query(sql);
		
		try {
			while(r.next())
			{
				Teachingstaff ts = new Teachingstaff();
				ts.setTsid(r.getString("tsid"));
				ts.setTsname(r.getString("tsname"));
				ts.setTstitle(r.getString("tstitle"));
				ts_list.add(ts);			
			}			
			r.close();
			db.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ts_list;
		
	}

}
