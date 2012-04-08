package server;


import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utility.MD5;

import model.User;


import database.DBUtility;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String email=request.getParameter("Email");
		String password=request.getParameter("Password");
		HttpSession session = request.getSession();
		
		DBUtility db = new DBUtility();
		ResultSet r = null;
		String sql = "Select * from Users where uemail=?";
		db.open();
		PreparedStatement p = db.query_prep(sql);		
		try {			
			p.setString(1, email);			
			r = p.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		User u = new User();
		
		try {
			if(r.next()){
				if(r.getString("upassword").equals(MD5.getMd5(password))){
					u.setUemail(email);
					u.setUnickname(r.getString("unickname"));
					u.setUserid(r.getString("userid"));
					u.setUpassword(null);
					u.setUnivid(r.getString("univid"));
					
					session.setAttribute("login", u);
					
					r.close();
					p.close();
					db.close();
					response.sendRedirect("/EasyCourse/index.jsp");
				}else{
					session.setAttribute("ErrorMessage", "Wrong Password!");	
					r.close();
					p.close();
					db.close();
					response.sendRedirect("/EasyCourse/error.jsp");
				}
			}else{
				session.setAttribute("ErrorMessage", "No Such User!");
				r.close();
				p.close();
				db.close();
				response.sendRedirect("/EasyCourse/error.jsp");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
