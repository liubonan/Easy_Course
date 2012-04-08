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

import database.DBUtility;

/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tbisbn=request.getParameter("isbn");
		String tbtitle=request.getParameter("title");
		String tbauthor=request.getParameter("author");
		String tbpublisher=request.getParameter("pulisher");
		String tbprice_str=request.getParameter("price");
		Double tbprice=0.0;
		
		if(!tbprice_str.equals("")){
			tbprice=Double.parseDouble(tbprice_str);
		}
		
		HttpSession session=request.getSession();
		
		if(ISBN_Check(tbisbn)){
			DBUtility db=new DBUtility();
			String sql="insert into Textbooks(tbISBN,tbtitle,tbauthor,tbpulisher,tbprice) values (?,?,?,?,?)";
			db.open();

			PreparedStatement p=db.edit_prep(sql);
			try {
				p.setString(1, tbisbn);
				p.setString(2, tbtitle);
				p.setString(3, tbauthor);
				p.setString(4, tbpublisher);
				p.setDouble(5, tbprice);
				p.executeUpdate();
				
				db.close();
				response.sendRedirect("/EasyCourse/browsebook.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			session.setAttribute("ErrorMessage", "Textbook already existed!");
			response.sendRedirect("/EasyCourse/error.jsp");
		}
		
		//session.setAttribute("ErrorMessage", null);
		//response.sendRedirect("/EasyCourse/error.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected boolean ISBN_Check(String isbn){
		boolean result=false;
		
		DBUtility db=new DBUtility();
		ResultSet r=null;
		String sql="select * from Textbooks where tbisbn=?";
		db.open();
		PreparedStatement p=db.query_prep(sql);
		try {
			p.setString(1, isbn);
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

}
