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

import model.SellInfo;
import model.Textbook;

import database.DBUtility;

/**
 * Servlet implementation class SellInfoServlet
 */
@WebServlet("/SellInfoServlet")
public class SellInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String isbn = request.getParameter("isbn");
		String sell_userid = request.getParameter("sell_userid");
		
		DBUtility db = new DBUtility();
		
		ResultSet r = null;
		String sql = "Select * from UsersSellTextbooks UST, Textbooks TB, Users U where UST.userid=? and " +
				"U.userid = UST.userid and TB.tbisbn = UST.tbisbn and UST.tbisbn=?";
		db.open();
		PreparedStatement p = db.query_prep(sql);		
		try {			
			p.setString(1, sell_userid);	
			p.setString(2, isbn);
			r = p.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		SellInfo si = new SellInfo();
		Textbook tb = new Textbook();
		
		try {
			if(r.next()){
				tb.setTbauthor(r.getString("tbauthor"));
				tb.setTbISBN(r.getString("tbisbn"));
				tb.setTbprice(r.getDouble("tbprice"));
				tb.setTbpublisher(r.getString("tbpulisher"));
				tb.setTbtitle(r.getString("tbtitle"));
				
				si.setCondition(r.getString("condition"));
				si.setNote(r.getString("note"));
				si.setSeller(r.getString("unickname"));
				si.setSellprice(r.getDouble("sellprice"));
				
				si.setTb(tb);
				
				session.setAttribute("sellinfo", si);
				r.close();
				p.close();
				db.close();				
				response.sendRedirect("/EasyCourse/saleinfo.jsp");
				
				
			}else{
				session.setAttribute("ErrorMessage", "No such sell infomation.");				
				db.close();
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
