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

import model.User;

import database.DBUtility;

/**
 * Servlet implementation class UpdateUserInfoServlet
 */
@WebServlet("/UpdateUserInfoServlet")
public class UpdateUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserInfoServlet() {
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
		String new_unickname=request.getParameter("nickname");
		String new_univid=request.getParameter("univ_list");
		
		HttpSession session=request.getSession();
		
		User u=(User)session.getAttribute("login");

		if(new_univid.equals("not_enrolled"))
			new_univid="";
		/*
		session.setAttribute("ErrorMessage", u.getUserid());
		response.sendRedirect("/EasyCourse/error.jsp");
		*/

		DBUtility db=new DBUtility();
		String sql="update Users set unickname=?, univid=? where userid=?";
		db.open();

		PreparedStatement p=db.edit_prep(sql);
		try {
			p.setString(1, new_unickname);
			p.setString(2, new_univid);
			p.setString(3, u.getUserid());
			p.executeUpdate();
			
			u.setUnickname(new_unickname);
			u.setUnivid(new_univid);
			
			session.setAttribute("login", u);
			db.close();
			response.sendRedirect("/EasyCourse/updateinfo.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
