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

import model.*;

import database.DBUtility;

/**
 * Servlet implementation class TextbookServlet
 */
@WebServlet("/TextbookServlet")
public class TextbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TextbookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String isbn=request.getParameter("isbn");
		
		HttpSession session = request.getSession();
		
		DBUtility db = new DBUtility();
		ResultSet r1 = null;
		ResultSet r2 = null;
		ResultSet r3 = null;
		ResultSet r4 = null;
		String sql1 = "select * from Textbooks where tbisbn=? ";
		String sql2 = "select * from TextbookComments TC, Users U where TC.tbisbn=? and U.userid=TC.userid order by TC.bctime asc";
		String sql3 = "select * from CoursesRequireTextbooks CRT, Courses C where CRT.tbisbn=? and CRT.cid=C.cid";
		String sql4 = "select * from UsersSellTextbooks UST, Users U where UST.tbisbn=? and U.userid=UST.userid";
		db.open();
		PreparedStatement p1 = db.query_prep(sql1);
		PreparedStatement p2 = db.query_prep(sql2);
		PreparedStatement p3 = db.query_prep(sql3);
		PreparedStatement p4 = db.query_prep(sql4);
		
		
		
		try {			
			p1.setString(1, isbn);	
			p2.setString(1, isbn);	
			p3.setString(1, isbn);	
			p4.setString(1, isbn);	
			r1= p1.executeQuery();
			r2= p2.executeQuery();
			r3= p3.executeQuery();
			r4= p4.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Textbook tb = new Textbook();
		List<TextbookComment> tc_list = new LinkedList<TextbookComment>();		
		List<Course> c_list = new LinkedList<Course>();
		List<User> u_list = new LinkedList<User>();
		
		try {
			if(r1.next()){
				tb.setTbauthor(r1.getString("tbauthor"));
				tb.setTbISBN(isbn);
				tb.setTbprice(r1.getDouble("tbprice"));
				tb.setTbpublisher(r1.getString("tbpulisher"));
				tb.setTbtitle(r1.getString("tbtitle"));
				
				while(r2.next())
				{
					TextbookComment tc = new TextbookComment();
					tc.setBcid(r2.getString("bcid"));
					tc.setBctitle(r2.getString("bctitle"));
					tc.setTbISBN(isbn);
					tc.setBctime(r2.getTimestamp("bctime"));								
					
					tc.setBccomment(db.ClobReader(r2.getClob("bccomment")));
					tc.setUserid(r2.getString("userid"));
					tc.setUnickname(r2.getString("unickname"));
					tc_list.add(tc);
				}
				
				while(r3.next())
				{
					Course c = new Course();
					c.setCid(r3.getString("cid"));
					c.setCname(r3.getString("cname"));
					c.setCdescription(db.ClobReader(r3.getClob("cdescription")));
					c_list.add(c);
				}
				
				while(r4.next())
				{
					User u = new User();
					u.setUemail(r4.getString("uemail"));
					u.setUserid(r4.getString("userid"));
					u.setUnickname(r4.getString("unickname"));
					u.setUpassword("");
					u_list.add(u);
				}
					
				session.setAttribute("textbook", tb);
				session.setAttribute("tcs", tc_list);
				session.setAttribute("cs", c_list);
				session.setAttribute("us", u_list);
				p1.close();
				p2.close();
				p3.close();
				p4.close();
				r1.close();
				r2.close();
				r3.close();
				r4.close();
				db.close();
				response.sendRedirect("/EasyCourse/book.jsp");
				
			}else{
				p1.close();
				p2.close();
				p3.close();
				p4.close();
				r1.close();
				r2.close();
				r3.close();
				r4.close();
				db.close();
				session.setAttribute("ErrorMessage", "No Such Book!");				
				response.sendRedirect("/EasyCourse/error.jsp");
			}
			
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
