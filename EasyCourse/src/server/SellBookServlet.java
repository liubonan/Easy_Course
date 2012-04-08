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

import database.DBUtility;

import model.Textbook;
import model.User;

/**
 * Servlet implementation class SellBookServlet
 */
@WebServlet("/SellBookServlet")
public class SellBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellBookServlet() {
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
		String condition = request.getParameter("condition");
		String note = request.getParameter("note").trim();
		
		String sellprice = request.getParameter("sell_price").trim();
		HttpSession session = request.getSession();
		double sell_price = 0;
		
		User u = (User)session.getAttribute("login");
		Textbook tb = (Textbook)session.getAttribute("textbook");
		
		if(sellprice == null || sellprice.equals(""))
			sellprice = "0";
		
		if(sellprice.matches("[0-9]*.?[0-9]*"))
			sell_price = Double.valueOf(sellprice);
		
		if(note == null || note.equals(""))
			note = "";
		
		String sql1 = "select * from UsersSellTextbooks where userid=? and tbisbn=?";
		String sql2 = "insert into UsersSellTextbooks values (?,?,?,?,?)";
		String sql3 = "select * from UsersSellTextbooks UST, Users U where UST.tbisbn=? and U.userid=UST.userid";
		
		DBUtility db = new DBUtility();
		ResultSet r1,r2;
		PreparedStatement p1,p2,p3;
		List<User> u_list = new LinkedList<User>();
		try {
			db.open();
			p1 = db.query_prep(sql1);
			p1.setString(1, u.getUserid());
			p1.setString(2, tb.getTbISBN());
			r1 = p1.executeQuery();
			
			if(r1.next())
			{
				r1.close();
				p1.close();
				r1.close();
				session.setAttribute("ErrorMessage", "You have posted sale information about this book before.");
				response.sendRedirect("/EasyCourse/error.jsp");
			}
			else
			{
				p2 = db.edit_prep(sql2);
				p2.setString(1, u.getUserid());
				p2.setString(2, tb.getTbISBN());
				p2.setDouble(3, sell_price);
				p2.setString(4, condition);
				p2.setString(5, note);
				
				p2.executeUpdate();
				
				p3 = db.query_prep(sql3);
				p3.setString(1, tb.getTbISBN());
				r2= p3.executeQuery();
				
				while(r2.next())
				{
					User su = new User();
					su.setUemail(r2.getString("uemail"));
					su.setUserid(r2.getString("userid"));
					su.setUnickname(r2.getString("unickname"));
					su.setUpassword("");
					u_list.add(su);
				}
				
				session.setAttribute("us", u_list);
				r1.close();				
				r2.close();				
				p1.close();
				p2.close();
				p3.close();
				db.close();
				response.sendRedirect("/EasyCourse/book.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
	}

}
