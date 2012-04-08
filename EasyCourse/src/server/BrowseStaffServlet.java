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

import database.DBUtility;

import model.Teachingstaff;
import model.Textbook;

/**
 * Servlet implementation class BrowseStaffServlet
 */
@WebServlet("/BrowseStaffServlet")
public class BrowseStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrowseStaffServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		List<Teachingstaff> ts_list = new LinkedList<Teachingstaff>();
		
		DBUtility db = new DBUtility();
		String sql = "select * from TeachingStaffs";
		ResultSet r;
		
		db.open();
		
		r = db.query(sql);
		
		try {
			while(r.next())
			{
				Teachingstaff ts = new Teachingstaff();
				ts.setTsid(r.getString("tsid"));
				ts.setTsname(r.getString("tsname"));
				ts.setTstitle(r.getString("tstitle"));
				ts_list.add(ts);				
				
			}
			session.setAttribute("tss", ts_list);
			r.close();
			db.close();
			response.sendRedirect("/EasyCourse/browsestaff.jsp");
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
