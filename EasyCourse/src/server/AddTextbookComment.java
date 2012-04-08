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


import model.Textbook;
import model.TextbookComment;
import model.User;

/**
 * Servlet implementation class AddTextbookComment
 */
@WebServlet("/AddTextbookComment")
public class AddTextbookComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTextbookComment() {
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
		User u = (User)session.getAttribute("login");
		Textbook tb = (Textbook)session.getAttribute("textbook");
		String ctitle = request.getParameter("ctitle").trim();
		String comment = request.getParameter("comment").trim();
		
		String bcid = null;
		
		if(comment== null || comment.equals(""))
		{
			session.setAttribute("ErrorMessage", "Comment cannot be empty!");				
			response.sendRedirect("/EasyCourse/error.jsp");
		}
		else
		{
		
			if(ctitle == null || ctitle.equals(""))
				ctitle = "Comment on "+tb.getTbtitle();
			
			String sql1 = "select bcid from TextbookComments order by bcid desc";
			String sql2 = "insert into TextbookComments values (?,?,'"+comment+"',?,?,sysdate)";
			String sql3 = "select * from TextbookComments TC, Users U where TC.tbisbn=? and U.userid=TC.userid order by TC.bctime asc";
			DBUtility db = new DBUtility();
			IdHelper idhelper = new IdHelper();
			
			ResultSet r,r3;
			try {
				db.open();
				r = db.query(sql1);
				
				if(r.next())
				{
					bcid = idhelper.nextId(r.getString("bcid"));
					r.close();
				}
				else
				{
					bcid = "BC000001";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			PreparedStatement p = db.edit_prep(sql2);
			PreparedStatement p3 = db.query_prep(sql3);
			
			List<TextbookComment> tc_list = new LinkedList<TextbookComment>();	
			
			try {
				p.setString(1, bcid);
				p.setString(2, ctitle);			
				p.setString(3, u.getUserid());
				p.setString(4, tb.getTbISBN());
				
				
				p.executeUpdate();
				p3.setString(1, tb.getTbISBN());
				r3 = p3.executeQuery();
				
				while(r3.next())
				{
					TextbookComment tc = new TextbookComment();
					tc.setBcid(r3.getString("bcid"));
					tc.setBctitle(r3.getString("bctitle"));
					tc.setTbISBN(tb.getTbISBN());
					tc.setBctime(r3.getTimestamp("bctime"));								
					
					tc.setBccomment(db.ClobReader(r3.getClob("bccomment")));
					tc.setUserid(r3.getString("userid"));
					tc.setUnickname(r3.getString("unickname"));
					tc_list.add(tc);			
					
				}
				
				r3.close();
				p.close();
				p3.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			db.close();
			session.setAttribute("tcs", tc_list);						
			response.sendRedirect("/EasyCourse/book.jsp");
		}
		
			
	}

}
