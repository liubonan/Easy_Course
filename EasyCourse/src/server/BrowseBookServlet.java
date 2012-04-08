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

import model.Textbook;
import model.TextbookComment;

/**
 * Servlet implementation class BrowseBook
 */
@WebServlet("/BrowseBookServlet")
public class BrowseBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrowseBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		List<Textbook> tb_list = new LinkedList<Textbook>();
		
		DBUtility db = new DBUtility();
		String sql = "select * from Textbooks";
		ResultSet r;
		
		db.open();
		
		r = db.query(sql);
		
		try {
			while(r.next())
			{
				Textbook tb = new Textbook();
				tb.setTbauthor(r.getString("tbauthor"));
				tb.setTbISBN(r.getString("tbISBN"));
				tb.setTbprice(r.getDouble("tbprice"));
				tb.setTbpublisher(r.getString("tbpulisher"));
				tb.setTbtitle(r.getString("tbtitle"));
				
				tb_list.add(tb);			
				
			}
			session.setAttribute("tbs", tb_list);
			r.close();
			db.close();
			response.sendRedirect("/EasyCourse/browsebook.jsp");
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
