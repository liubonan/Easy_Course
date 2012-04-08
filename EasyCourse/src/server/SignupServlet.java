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

import utility.IdHelper;
import utility.MD5;


import database.DBUtility;

import model.User;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
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
		
		String uemail=request.getParameter("email");
		String unickname=request.getParameter("nickname");
		String upassword=request.getParameter("password");
		String userid=generateID();
		String univid=request.getParameter("univ_list");
		
		HttpSession session=request.getSession();
		
		User u=new User();
		
		if(univid.equals("not_enrolled"))
			univid="";
		/*
		session.setAttribute("ErrorMessage",univid);
		response.sendRedirect("/EasyCourse/error.jsp");
		*/
		
		if(Email_Check(uemail)){
			DBUtility db=new DBUtility();
			String sql="insert into Users(userid,uemail,unickname,upassword,univid) values (?,?,?,?,?)";
			db.open();

			PreparedStatement p=db.edit_prep(sql);
			
			try {
				p.setString(1,userid);
				p.setString(2,uemail);
				p.setString(3,unickname);
				p.setString(4,MD5.getMd5(upassword));
				p.setString(5,univid);
				
				p.executeUpdate();
				
				u.setUemail(uemail);
				u.setUnickname(unickname);
				u.setUpassword(null);
				u.setUserid(userid);
				u.setUnivid(univid);
				
				session.setAttribute("login", u);
				db.close();
				response.sendRedirect("/EasyCourse/index.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			session.setAttribute("ErrorMessage", "Email has been used! Please try another one.");
			response.sendRedirect("/EasyCourse/error.jsp");
		}
		
	}
	
	
	protected boolean Email_Check(String email)
	{
		boolean result=false;
		
		DBUtility db=new DBUtility();
		ResultSet r=null;
		String sql="select * from Users where uemail=?";
		db.open();
		PreparedStatement p=db.query_prep(sql);
		try {
			p.setString(1, email);
			r=p.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if(r.next()){
				result=false;
				db.close();
			}
			else{
				result=true;
				db.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	protected String generateID(){
		String userid="";
		
		DBUtility db=new DBUtility();
		ResultSet r=null;
		String sql="select * from Users order by userid desc";
		db.open();
		PreparedStatement p = db.query_prep(sql);
		try {
			r=p.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String currentid="";
		IdHelper idhelp=new IdHelper();
		try {
			if(r.next()){
				currentid=r.getString("userid");
				userid=idhelp.nextId(currentid);
				db.close();
			}
			else{
				userid="U0000001";
				db.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userid;
	}

}
