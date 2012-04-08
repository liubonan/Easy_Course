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

import model.TeachingstaffComment;
import utility.IdHelper;
import database.DBUtility;

/**
 * Servlet implementation class AddCourseServlet
 */
@WebServlet("/AddCourseServlet")
public class AddCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCourseServlet() {
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
		String cname = request.getParameter("cname").trim();
		String tsid = request.getParameter("tsid").trim();
		String univid = request.getParameter("univid").trim();
		String csemester = request.getParameter("csemester").trim().toUpperCase();
		String cdescription = request.getParameter("cdescription").trim();
		
		if(cname==null || cname.equals("")||csemester == null || csemester.equals(""))
		{
			session.setAttribute("ErrorMessage", "Please input the required field");
			response.sendRedirect("/EasyCourse/error.jsp");
		}
		else if(!(csemester.matches("[0-9]{4}((SPRING)|(FALL)|(SUMMER))") ))
		{
			session.setAttribute("ErrorMessage", "Semester field should be in the following format: <br>2011Fall<br>2012Spring<br>2012Summer");
			response.sendRedirect("/EasyCourse/error.jsp");
		}
		else
		{
			
			if(cdescription == null || cdescription.equals(""))
				cdescription = "N/A";		
			
			String name_cmp = new String(cname.toLowerCase().replace("+","").replace(" ", "").replace("-", "").replace("_", "").replace(",", "").replace(";", ""));
			boolean name_flag = false;
			String cid = null;
			String nextcid=null;
			int index=0;
			
			String sql1 = "select * from Courses order by cid desc";
			DBUtility db = new DBUtility();
			IdHelper idhelper = new IdHelper();
			db.open();
			
			ResultSet r,r3;
			try {
				
				r = db.query(sql1);
				
				while(r.next())
				{
					if(index == 0)
						nextcid = r.getString("cid");
					
					index ++;
					
					if(name_cmp.equals(r.getString("cname").toLowerCase().replace("+","").replace(" ", "").replace("-", "").replace("_", "").replace(",", "").replace(";", "")))
					{
						name_flag=true;		
						cid = r.getString("cid");
						r.close();
						break;
					}					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			String sqlcid = "insert into Courses values(?,?,'"+cdescription+"')";
			
			if(cid == null)
			{
				IdHelper ih=new IdHelper();
				PreparedStatement p2 = db.edit_prep(sqlcid);
				cid = ih.nextId(nextcid);
				try {
					p2.setString(1,  cid);
					p2.setString(2, cname);
					p2.executeUpdate();
					p2.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
			}
			
			
			String sql2 = "insert into TeachingStaffsTeachCourses values (?,?,?)";
			String sql3 = "insert into UnivOpenCourses values (?,?)";
			
			String sql20 = "select * from TeachingStaffsTeachCourses where tid=? and cid=? and semester=?";
			String sql30 = "select * from UnivOpenCourses where univid=? and cid=?";
			
			PreparedStatement p = db.edit_prep(sql2);
			PreparedStatement p3 = db.edit_prep(sql3);
			
			PreparedStatement p20 = db.query_prep(sql20);
			PreparedStatement p30 = db.query_prep(sql30);
			
			try {
				p.setString(1, tsid);
				p.setString(2, cid);			
				p.setString(3, csemester);
				
				p20.setString(1, tsid);
				p20.setString(2, cid);			
				p20.setString(3, csemester);
				
				ResultSet r20=p20.executeQuery();
				
				p3.setString(1, univid);
				p3.setString(2, cid);
				p30.setString(1, univid);
				p30.setString(2, cid);
				ResultSet r30=p30.executeQuery();
				
				if(r20.next())					
				{
					session.setAttribute("ErrorMessage", "This teacher has opened this course in this semester.");
					response.sendRedirect("/EasyCourse/error.jsp");					
				}				
				else if(r30.next())
				{
					p.executeUpdate();					
					p.close();
					p3.close();
					db.close();
					response.sendRedirect("/EasyCourse/CourseServlet?cid="+cid);
				}
				else 
				{
					p.executeUpdate();
					p3.executeUpdate();
					p.close();
					p3.close();
					db.close();
					response.sendRedirect("/EasyCourse/CourseServlet?cid="+cid);
				}
				
							
				
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}

}
