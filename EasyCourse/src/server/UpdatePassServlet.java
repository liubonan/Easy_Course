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

import database.DBUtility;

import model.User;

/**
 * Servlet implementation class UpdatePassServlet
 */
@WebServlet("/UpdatePassServlet")
public class UpdatePassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePassServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String current_pass=request.getParameter("curr_pass");
		String new_pass=request.getParameter("new_pass");
		
		HttpSession session=request.getSession();
		
		User u=(User)session.getAttribute("login");
		
		if(Pass_Check(current_pass,u.getUserid())){
			//session.setAttribute("ErrorMessage", "success");

			DBUtility db=new DBUtility();
			String sql="update Users set upassword=? where userid=?";
			db.open();
			PreparedStatement p=db.query_prep(sql);
			try {
				p.setString(1, new_pass);
				p.setString(2, u.getUserid());
				p.executeUpdate();
				
				session.setAttribute("login", u);
				db.close();

				response.sendRedirect("/EasyCourse/updateinfo.jsp");
				 
				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else{
			session.setAttribute("ErrorMessage", "Wrong password.");
			response.sendRedirect("/EasyCourse/error.jsp");
		}
		
	}
	
	protected boolean Pass_Check(String current_pass, String userid){
		boolean result=false;
		
		DBUtility db=new DBUtility();
		ResultSet r=null;
		String sql="select * from Users where userid=?";
		db.open();
		PreparedStatement p=db.query_prep(sql);
		try {
			p.setString(1, userid);
			r=p.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if(r.next()){
				if(current_pass.equals(r.getString("upassword"))){
					result=true;
					db.close();
				}
				else{
					result=false;
					db.close();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	

}
