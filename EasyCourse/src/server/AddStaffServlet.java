package server;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utility.IdHelper;
import database.DBUtility;

/**
 * Servlet implementation class AddStaffServlet
 */
@WebServlet("/AddStaffServlet")
public class AddStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStaffServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tsid=generateID();
		String tsname=request.getParameter("name");
		String tstitle=request.getParameter("title");
		
		String workinginfo_status=request.getParameter("workinginfo");
		
		HttpSession session=request.getSession();
		
		DBUtility db=new DBUtility();
		String sql1="insert into TeachingStaffs(tsid,tsname,tstitle) values(?,?,?)";
		db.open();
		PreparedStatement p1=db.edit_prep(sql1);
		try {
			p1.setString(1, tsid);
			p1.setString(2, tsname);
			p1.setString(3, tstitle);

			if(workinginfo_status.equals("workinginfo_enabled")){
				String univid=request.getParameter("univ_list");

				String sdate=request.getParameter("sdate");
				String edate=request.getParameter("edate");

				String tid=tsid;
				if(!sdate.equals("")){
					if(Format_Check(sdate)){
						String sql3="";
						if(!edate.equals("")){
							if(Format_Check(edate)){
								if(CompareDate(sdate,edate)){
									p1.executeQuery();
									
									sql3="insert into TStaffsWorksInUniv(tid,univid,startdate,enddate) values(?,?,to_date(?,'dd-MM-yyyy'),to_date(?,'dd-MM-yyyy'))";
									PreparedStatement p2=db.edit_prep(sql3);
									p2.setString(1, tid);
									p2.setString(2, univid);
									p2.setString(3, sdate);
									p2.setString(4, edate);
									p2.executeUpdate();
									
									response.sendRedirect("/EasyCourse/index.jsp");
								}
								else{
									db.close();
									session.setAttribute("ErrorMessage", "Invalid end time, must be after start time");
									response.sendRedirect("/EasyCourse/error.jsp");
								}
								
							}
							else{
								db.close();
								session.setAttribute("ErrorMessage", "Invalid format");
								response.sendRedirect("/EasyCourse/error.jsp");
							}

						}
						else{
							p1.executeQuery();
							
							sql3="insert into TStaffsWorksInUniv(tid,univid,startdate) values(?,?,to_date(?,'dd-MM-yyyy'))";
							PreparedStatement p2=db.edit_prep(sql3);
							p2.setString(1, tid);
							p2.setString(2, univid);
							p2.setString(3, sdate);
							p2.executeUpdate();
							
							response.sendRedirect("/EasyCourse/index.jsp");
						}
					}
					else{
						db.close();
						session.setAttribute("ErrorMessage", "Invalid format");
						response.sendRedirect("/EasyCourse/error.jsp");
					}
				}
				else{
					db.close();
					session.setAttribute("ErrorMessage", "Start time is required!");
					response.sendRedirect("/EasyCourse/error.jsp");
				}

			}
			else{
				p1.executeQuery();
				db.close();
				response.sendRedirect("/EasyCourse/index.jsp");
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
	
	protected String generateID(){
		String tsid="";
		
		DBUtility db=new DBUtility();
		ResultSet r=null;
		String sql="select * from TeachingStaffs order by tsid desc";
		db.open();
		PreparedStatement p = db.query_prep(sql);
		try {
			r=p.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String currentid="";
		IdHelper idhelp=new IdHelper();
		try {
			if(r.next()){
				currentid=r.getString("tsid");
				tsid=idhelp.nextId(currentid);
				db.close();
			}
			else{
				tsid="T0000001";
				db.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tsid;
	}

	protected boolean Format_Check(String date_str){
		boolean result=false;
		
		Pattern pattern=Pattern.compile("(((0[1-9]|[12][0-9]|3[01])-((0[13578]|1[02]))|((0[1-9]|[12][0-9]|30)-(0[469]|11))|(0[1-9]|[1][0-9]|2[0-8])-(02))-([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3}))|(29-02-(([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00)))");
		if(pattern.matcher(date_str).matches()){
			result=true;
		}
		return result;
		
	}

	protected boolean CompareDate(String sdate,String edate){
		boolean result=false;
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
		try {
			java.util.Date sdate_util=format.parse(sdate);
			java.util.Date edate_util=format.parse(edate);
			
			result=sdate_util.before(edate_util);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
