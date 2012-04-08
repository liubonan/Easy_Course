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
//import javax.servlet.http.HttpSession;

import utility.IdHelper;
import database.DBUtility;

/**
 * Servlet implementation class AddUniversityServlet
 */
@WebServlet("/AddUniversityServlet")
public class AddUniversityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUniversityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String univid=generateID();
		String univname=request.getParameter("name");
		String univwebsite=request.getParameter("website");

		//HttpSession session=request.getSession();
		
		DBUtility db=new DBUtility();
		String sql="insert into Universities(univid,univname,univwebsite) values(?,?,?)";
		db.open();
		PreparedStatement p=db.query_prep(sql);
		try {
			p.setString(1, univid);
			p.setString(2, univname);
			p.setString(3, univwebsite);
			p.executeUpdate();
			
			db.close();
			response.sendRedirect("/EasyCourse/index.jsp");
			//session.setAttribute("ErrorMessage",univwebsite);
			//response.sendRedirect("/EasyCourse/error.jsp");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//session.setAttribute("ErrorMessage", univid);
		//response.sendRedirect("/EasyCourse/error.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected String generateID(){
		String univid="";
		
		DBUtility db=new DBUtility();
		ResultSet r=null;
		String sql="select * from Universities order by univid desc";
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
				currentid=r.getString("univid");
				univid=idhelp.nextId(currentid);
				db.close();
			}
			else{
				univid="UN000001";
				db.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return univid;
	}

}
