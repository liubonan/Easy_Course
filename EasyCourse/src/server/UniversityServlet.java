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

import model.*;
import database.DBUtility;

/**
 * Servlet implementation class UniversityServlet
 */
@WebServlet("/UniversityServlet")
public class UniversityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UniversityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String univid=request.getParameter("univid");
		
		HttpSession session = request.getSession();
		
		DBUtility db = new DBUtility();
		ResultSet r1 = null;
		ResultSet r2 = null;
		ResultSet r3 = null;
		ResultSet r4 = null;
		String sql1 = "select * from universities where univid=?";
		String sql2 = "select * from UnivOpenCourses UOC, Courses C where UOC.univid=? and UOC.cid=C.cid";
		String sql3 = "select * from Users where univid=?";
		String sql4 = "select * from TStaffsWorksInUniv TWU, TeachingStaffs TS where TWU.univid=? and TWU.tid = TS.tsid";

		db.open();
		PreparedStatement p1 = db.query_prep(sql1);
		PreparedStatement p2 = db.query_prep(sql2);
		PreparedStatement p3 = db.query_prep(sql3);
		PreparedStatement p4 = db.query_prep(sql4);	
		try {			
			p1.setString(1, univid);
			p2.setString(1, univid);
			p3.setString(1, univid);
			p4.setString(1, univid);
			r1= p1.executeQuery();
			r2= p2.executeQuery();
			r3= p3.executeQuery();
			r4= p4.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		University univ = new University();
		List<Course> c_list = new LinkedList<Course>();
		List<User> u_list = new LinkedList<User>();
		List<Teachingstaff> ts_list= new LinkedList<Teachingstaff>();
		
		try {
			if(r1.next()){
				univ.setUnivid(univid);
				univ.setUnivname(r1.getString("univname"));
				univ.setUnivwebsite(r1.getString("univwebsite"));
				
				while(r2.next())
				{
					Course c = new Course();
					c.setCid(r2.getString("cid"));
					c.setCname(r2.getString("cname"));
					c.setCdescription("");
					c_list.add(c);
				}
				
				while(r3.next())
				{
					User u = new User();
					u.setUemail(r3.getString("uemail"));
					u.setUserid(r3.getString("userid"));
					u.setUnickname(r3.getString("unickname"));					
					u.setUpassword("");
					u_list.add(u);
				}
				
				while(r4.next())
				{
					Teachingstaff ts = new Teachingstaff();
					ts.setTsid(r4.getString("tid"));
					ts.setTsname(r4.getString("tsname"));
					ts.setTstitle(r4.getString("tstitle"));
					ts_list.add(ts);					
				}
					
				session.setAttribute("univ", univ);
				session.setAttribute("ucs", c_list);
				session.setAttribute("uus", u_list);
				session.setAttribute("uts", ts_list);
				
				p1.close();
				p2.close();
				p3.close();
				p4.close();
				r1.close();
				r2.close();
				r3.close();
				r4.close();
				db.close();
				response.sendRedirect("/EasyCourse/university.jsp");
				
			}else{
				session.setAttribute("ErrorMessage", "No Such University!");
				p1.close();
				p2.close();
				p3.close();
				p4.close();
				r1.close();
				r2.close();
				r3.close();
				r4.close();
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
