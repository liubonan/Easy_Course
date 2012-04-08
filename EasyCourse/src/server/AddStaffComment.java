package server;

import java.io.IOException;
import java.sql.PreparedStatement;
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

import utility.IdHelper;
import database.DBUtility;

import model.*;

/**
 * Servlet implementation class AddStaffComment
 */
@WebServlet("/AddStaffComment")
public class AddStaffComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStaffComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("login");
		Teachingstaff ts = (Teachingstaff)session.getAttribute("staff"); 
		String ctitle = request.getParameter("ctitle").trim();
		String comment = request.getParameter("comment").trim();
		
		String scid = null;
		
		if(comment== null || comment.equals(""))
		{
			session.setAttribute("ErrorMessage", "Comment cannot be empty!");	
			response.sendRedirect("/EasyCourse/error.jsp");
		}
		else
		{
		
			if(ctitle == null || ctitle.equals(""))
				ctitle = "Comment on "+ts.getTsname();
			
			String sql1 = "select scid from TeachingStaffComments order by scid desc";
			String sql2 = "insert into TeachingStaffComments values (?,?,'"+comment+"',?,?,sysdate)";
			String sql3 = "select * from TeachingStaffComments TSC, Users U where TSC.userid=U.userid and TSC.tsid=? order by TSC.sctime asc";
			DBUtility db = new DBUtility();
			IdHelper idhelper = new IdHelper();
			
			ResultSet r,r3;
			try {
				db.open();
				r = db.query(sql1);
				
				if(r.next())
				{
					scid = idhelper.nextId(r.getString("scid"));
					r.close();
				}
				else
				{
					scid = "SC000001";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			PreparedStatement p = db.edit_prep(sql2);
			PreparedStatement p3 = db.query_prep(sql3);
			
			List<TeachingstaffComment> tsc_list = new LinkedList<TeachingstaffComment>();	
			
			try {
				p.setString(1, scid);
				p.setString(2, ctitle);			
				p.setString(3, u.getUserid());
				p.setString(4, ts.getTsid());
				
				
				p.executeUpdate();
				p3.setString(1, ts.getTsid());
				r3 = p3.executeQuery();
				
				while(r3.next())
				{
					TeachingstaffComment tc = new TeachingstaffComment();
					tc.setTsid(ts.getTsid());
					tc.setSctime(r3.getTimestamp("sctime"));
					tc.setSctitle(r3.getString("sctitle"));
					tc.setScid(r3.getString("scid"));
					tc.setUserid(r3.getString("userid"));
					tc.setUnickname(r3.getString("unickname"));
					tc.setSccomment(db.ClobReader(r3.getClob("sccomment")));				
					
					tsc_list.add(tc);		
					
				}
				
				r3.close();
				p.close();
				p3.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			db.close();
			session.setAttribute("tscs", tsc_list);						
			response.sendRedirect("/EasyCourse/staff.jsp");
		}
	}

}
