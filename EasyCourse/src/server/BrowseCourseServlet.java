package server;

import java.io.IOException;
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
import database.DBUtility;

/**
 * Servlet implementation class BrowseCourseServlet
 */
@WebServlet("/BrowseCourseServlet")
public class BrowseCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrowseCourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		List<Course> c_list = new LinkedList<Course>();
		
		DBUtility db = new DBUtility();
		String sql = "select * from Courses";
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
				c_list.add(c);				
				
			}
			session.setAttribute("cs", c_list);
			r.close();
			db.close();
			response.sendRedirect("/EasyCourse/browsecourse.jsp");
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
