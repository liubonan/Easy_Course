package utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import database.DBUtility;

import model.Course;
import model.Textbook;
import model.User;

public class IndexHelper {
	
	public IndexHelper(){}
	
	public List<Course> getCourses()
	{
		List<Course> result = new LinkedList<Course>();
		
		DBUtility db = new DBUtility();
		String sql = "select * from Courses order by cid desc";
		ResultSet r;		
		db.open();		
		r = db.query(sql);		
		try {
			while(r.next())
			{
				Course c = new Course();
				c.setCid(r.getString("cid"));
				c.setCname(r.getString("cname"));
				c.setCdescription(db.ClobReader(r.getClob("cdescription")));
				result.add(c);					
			}			
			r.close();
			db.close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return result;		
	}
	
	public List<Textbook> getTextbook()
	{
		List<Textbook> result = new LinkedList<Textbook>();
		
		DBUtility db = new DBUtility();
		String sql = "select * from Textbooks TB order by (select count(cid) from CoursesRequireTextbooks CRT where CRT.tbisbn=TB.tbisbn) desc";
		ResultSet r;
		
		db.open();
		
		r = db.query(sql);
		
		try {
			while(r.next())
			{
				Textbook tb = new Textbook();
				tb.setTbauthor(r.getString("tbauthor"));
				tb.setTbISBN(r.getString("tbISBN"));
				tb.setTbprice(r.getDouble("tbprice"));
				tb.setTbpublisher(r.getString("tbpulisher"));
				tb.setTbtitle(r.getString("tbtitle"));
				
				result.add(tb);			
				
			}
			
			r.close();
			db.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<List<Course>> getMyCourses(String userid)
	{
		List<List<Course>> result = new LinkedList<List<Course>>();	
		
		List<Course>plan = new LinkedList<Course>();
		List<Course>taking = new LinkedList<Course>();
		List<Course>completed = new LinkedList<Course>();		
		
		DBUtility db = new DBUtility();
		String sql = "select * from Courses C, UsersTakeCourses UTC where C.cid = UTC.cid and UTC.userid = '"+userid+"'";
		ResultSet r;		
		db.open();		
		r = db.query(sql);		
		try {
			while(r.next())
			{
				Course c = new Course();
				c.setCid(r.getString("cid"));
				c.setCname(r.getString("cname"));
				c.setCdescription(db.ClobReader(r.getClob("cdescription")));
				
				String status = r.getString("status");
				
				if(status.equals("Plan"))
					plan.add(c);
				
				if (status.equals("Taking"))
					taking.add(c);
				
				if (status.equals("Completed"))
					completed.add(c);
					
			}			
			r.close();
			db.close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.add(plan);
		result.add(taking);
		result.add(completed);
		return result;		
	}
	

}
