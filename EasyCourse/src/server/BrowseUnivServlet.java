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

import model.Teachingstaff;
import model.University;
import database.DBUtility;

/**
 * Servlet implementation class BrowseUnivServlet
 */
@WebServlet("/BrowseUnivServlet")
public class BrowseUnivServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrowseUnivServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		List<University> univ_list = new LinkedList<University>();
		
		DBUtility db = new DBUtility();
		String sql = "select * from Universities";
		ResultSet r;
		
		db.open();
		
		r = db.query(sql);
		
		try {
			while(r.next())
			{
				University univ = new University();
				univ.setUnivid(r.getString("univid"));
				univ.setUnivname(r.getString("univname"));
				univ.setUnivwebsite(r.getString("univwebsite"));
				univ_list.add(univ);				
				
			}
			session.setAttribute("univs", univ_list);
			r.close();
			db.close();
			response.sendRedirect("/EasyCourse/browseuniv.jsp");
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
