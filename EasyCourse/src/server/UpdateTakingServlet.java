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
import model.User;

/**
 * Servlet implementation class UpdateTakingServlet
 */
@WebServlet("/UpdateTakingServlet")
public class UpdateTakingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTakingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String status=request.getParameter("status");
		User u = (User)session.getAttribute("login");
		Course c = (Course)session.getAttribute("course");
		Integer take_flag = (Integer)session.getAttribute("take");
		DBUtility db = new DBUtility();
		
		if(status == null || status.equals(""))
		{
			session.setAttribute("ErrorMessage", "You need to select a status to update");				
			response.sendRedirect("/EasyCourse/error.jsp");
		}
		else
		{
			db.open();
			if(take_flag == 0)
			{
				String sql1 = "insert into UsersTakeCourses values (?,?,?)";
				PreparedStatement p1 = db.edit_prep(sql1);
				try {
					p1.setString(1, u.getUserid());
					p1.setString(2, c.getCid());
					p1.setString(3, status);
					p1.executeUpdate();
					p1.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			else
			{
				String sql2 = "update UsersTakeCourses set status=? where userid=? and cid=?";
				PreparedStatement p2 = db.edit_prep(sql2);
				try {
					p2.setString(1, status);
					p2.setString(2, u.getUserid());
					p2.setString(3, c.getCid());
					p2.executeUpdate();
					p2.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			db.close();
			response.sendRedirect("/EasyCourse/CourseServlet?cid="+c.getCid());
			
			
		}
		
	}

}
