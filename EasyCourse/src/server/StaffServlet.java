package server;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;
import database.DBUtility;

/**
 * Servlet implementation class StaffServlet
 */
@WebServlet("/StaffServlet")
public class StaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tsid=request.getParameter("tsid");
		HttpSession session = request.getSession();
		
		DBUtility db = new DBUtility();
		ResultSet r0 = null;
		ResultSet r1 = null;
		ResultSet r2 = null;
		ResultSet r3 = null;
		String sql0 = "select * from TeachingStaffs where tsid=?";
		String sql1 = "select * from TeachingStaffs TS,TStaffsWorksInUniv TWU, Universities U where tsid=? and TS.tsid = TWU.tid and TWU.univid = U.univid order by startdate desc";
		String sql2 = "select * from TeachingStaffComments TSC, Users U where TSC.userid=U.userid and TSC.tsid=?";
		String sql3 = "select * from TeachingStaffsTeachCourses TT, Courses C where TT.tid=? and C.cid = TT.cid";
		db.open();
		PreparedStatement p0 = db.query_prep(sql0);
		PreparedStatement p1 = db.query_prep(sql1);
		PreparedStatement p2 = db.query_prep(sql2);
		PreparedStatement p3 = db.query_prep(sql3);
		try {
			p0.setString(1, tsid);
			p1.setString(1, tsid);
			p2.setString(1, tsid);
			p3.setString(1, tsid);
			r0 = p0.executeQuery();
			r1 = p1.executeQuery();
			r2 = p2.executeQuery();
			r3 = p3.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Teachingstaff ts = new Teachingstaff();
		University univ = new University();
		Date startdate = null, enddate=null;
		List<TeachingstaffComment> tsc_list = new LinkedList<TeachingstaffComment>();		
		List<Course> c_list = new LinkedList<Course>();
		
		try {
			if(r0.next()){
				ts.setTsid(tsid);
				ts.setTsname(r0.getString("tsname"));
				ts.setTstitle(r0.getString("tstitle"));
				
				if(r1.next()){
				univ.setUnivid(r1.getString("univid"));
				univ.setUnivname(r1.getString("univname"));
				univ.setUnivwebsite(r1.getString("univwebsite"));
				startdate = r1.getDate("startdate");
				enddate=r1.getDate("enddate");
				}
				
				
				while(r2.next())
				{
					TeachingstaffComment tc = new TeachingstaffComment();
					tc.setTsid(tsid);
					tc.setSctime(r2.getTimestamp("sctime"));
					tc.setSctitle(r2.getString("sctitle"));
					tc.setScid(r2.getString("scid"));
					tc.setUserid(r2.getString("userid"));
					tc.setUnickname(r2.getString("unickname"));
					
					if(r2.getClob("sccomment") != null)
						tc.setSccomment(db.ClobReader(r2.getClob("sccomment")));
					else
						tc.setSccomment("N/A");
					
					tsc_list.add(tc);
				}
				
				while(r3.next())
				{
					Course c = new Course();
					c.setCid(r3.getString("cid"));
					c.setCname(r3.getString("cname"));
					
					if(r3.getClob("cdescription") != null)
						c.setCdescription(db.ClobReader(r3.getClob("cdescription")));
					else
						c.setCdescription("N/A");
					
					c.setSemester(r3.getString("semester"));
					c_list.add(c);
				}
				
				session.setAttribute("staff", ts);
				
				if(!(startdate==null))
					session.setAttribute("startdate", startdate.toString());
				else
					session.setAttribute("startdate", "N/A");
				
				if(enddate == null)
					session.setAttribute("enddate", "");
				else
					session.setAttribute("enddate", enddate.toString());
				
				session.setAttribute("tsuniv", univ);
				session.setAttribute("tscs", tsc_list);
				session.setAttribute("scs", c_list);
				p0.close();
				p1.close();
				p2.close();
				p3.close();
				r0.close();
				r1.close();
				r2.close();
				r3.close();
				db.close();
				response.sendRedirect("/EasyCourse/staff.jsp");
				
			}else{
				session.setAttribute("ErrorMessage", "No Such Staff!");
				p0.close();
				p1.close();
				p2.close();
				p3.close();
				r0.close();
				r1.close();
				r2.close();
				r3.close();
				db.close();
				response.sendRedirect("/EasyCourse/error.jsp");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
