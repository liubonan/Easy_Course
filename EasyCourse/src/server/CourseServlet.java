package server;

import java.io.IOException;
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

import model.Course;
import model.Teachingstaff;
import model.Textbook;
import model.TextbookComment;
import model.University;
import model.User;

import database.DBUtility;

/**
 * Servlet implementation class CourseServlet
 */
@WebServlet("/CourseServlet")
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cid=request.getParameter("cid");
		HttpSession session = request.getSession();
		
		User u = (User)session.getAttribute("login");
		
		DBUtility db = new DBUtility();
		ResultSet r1 = null;
		ResultSet r2 = null;
		ResultSet r3 = null;
		ResultSet r4 = null;
		ResultSet r5 = null;
		String sql1 = "select * from Courses where cid=? ";
		String sql2 = "select distinct tid,tsname,tstitle from TeachingStaffsTeachCourses TSTC, TeachingStaffs TS where TSTC.cid=? and TS.tsid = TSTC.tid";
		String sql3 = "select distinct UNIV.univid AS univid, univname, univwebsite from UnivOpenCourses UOC, Universities UNIV where UOC.cid=? and UNIV.univid=UOC.univid";
		String sql4 = "select status from UsersTakeCourses UTC where UTC.cid=? and UTC.userid=?";
		String sql5 = "select * from CoursesRequireTextbooks CRT, Textbooks TB where CRT.cid =? and TB.tbisbn = CRT.tbisbn";
		db.open();
		PreparedStatement p1 = db.query_prep(sql1);
		PreparedStatement p2 = db.query_prep(sql2);
		PreparedStatement p3 = db.query_prep(sql3);
		PreparedStatement p4 = db.query_prep(sql4);
		PreparedStatement p5 = db.query_prep(sql5);
		
		try {			
			p1.setString(1, cid);	
			p2.setString(1, cid);	
			p3.setString(1, cid);	
			p4.setString(1, cid);
			
			if(u != null)
				p4.setString(2, u.getUserid());
			else
				p4.setString(2, " ");
			
			p5.setString(1, cid);	
			r1= p1.executeQuery();
			r2= p2.executeQuery();
			r3= p3.executeQuery();
			r4= p4.executeQuery();
			r5= p5.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Course c = new Course();
		List<Teachingstaff> ts_list = new LinkedList<Teachingstaff>();
		List<University> univ_list = new LinkedList<University>();
		Integer take_flag = 0;
		List<Textbook> tb_list = new LinkedList<Textbook>();		
		
		
		try {
			if(r1.next()){
				c.setCid(cid);
				c.setCname(r1.getString("cname"));
				c.setCdescription(db.ClobReader(r1.getClob("cdescription")));
				c.setSemester("N/A");
				
				while(r2.next())
				{
					Teachingstaff ts = new Teachingstaff();
					ts.setTsid(r2.getString("tid"));
					ts.setTsname(r2.getString("tsname"));
					ts.setTstitle(r2.getString("tstitle"));
					ts_list.add(ts);
				}
				
				while(r3.next())
				{
					University univ = new University();
					univ.setUnivid(r3.getString("univid"));
					univ.setUnivname(r3.getString("univname"));
					univ.setUnivwebsite(r3.getString("univwebsite"));
					univ_list.add(univ);
				}
				
				if(r4.next())
					take_flag =to_flag( r4.getString("status"));
				
				while(r5.next())
				{					
					Textbook tb = new Textbook();
					tb.setTbauthor(r5.getString("tbauthor"));
					tb.setTbISBN(r5.getString("tbISBN"));
					tb.setTbprice(r5.getDouble("tbprice"));
					tb.setTbpublisher(r5.getString("tbpulisher"));
					tb.setTbtitle(r5.getString("tbtitle"));
					tb_list.add(tb);
				}
					
				session.setAttribute("course", c);
				session.setAttribute("tss", ts_list);
				session.setAttribute("univs", univ_list);
				session.setAttribute("take",take_flag);
				session.setAttribute("tbs", tb_list);				
				p1.close();
				p2.close();
				p3.close();
				p4.close();
				r1.close();
				r2.close();
				r3.close();
				r4.close();
				db.close();
				response.sendRedirect("/EasyCourse/course.jsp");
				
			}else{
				p1.close();
				p2.close();
				p3.close();
				p4.close();
				r1.close();
				r2.close();
				r3.close();
				r4.close();
				db.close();
				session.setAttribute("ErrorMessage", "No Such Course!");				
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
	
	protected int to_flag(String status)
	{
		if(status.equals("Plan"))
			return 1;
		else if (status.equals("Taking"))
			return 2;
		else if (status.equals("Completed"))
			return 3;
		
		return 0;
	}

}
