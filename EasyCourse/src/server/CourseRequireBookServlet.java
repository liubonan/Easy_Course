package server;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DBUtility;

import model.Course;

/**
 * Servlet implementation class CourseRequireBookServlet
 */
@WebServlet("/CourseRequireBookServlet")
public class CourseRequireBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseRequireBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tbISBN=request.getParameter("book_list");
		
		HttpSession session=request.getSession();
		
		Course c=new Course();
		c=(Course)session.getAttribute("course");
		String courseid=c.getCid();
		
		DBUtility db=new DBUtility();
		String sql="insert into CoursesRequireTextbooks(cid,tbISBN) values(?,?)";
		db.open();
		PreparedStatement p=db.edit_prep(sql);
		try {
			p.setString(1, courseid);
			p.setString(2, tbISBN);
			p.executeUpdate();
			
			
			
			//session.setAttribute("ErrorMessage", courseid+tbISBN);
			response.sendRedirect("/EasyCourse/CourseServlet?cid="+courseid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//session.setAttribute("ErrorMessage", c.getCname());
		//response.sendRedirect("/EasyCourse/error.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
