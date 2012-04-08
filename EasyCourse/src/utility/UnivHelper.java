package utility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DBUtility;

import model.University;

public class UnivHelper {
	public UnivHelper(){
		
	}
	
	public List<University> getUnivList(){
		DBUtility db=new DBUtility();
		ResultSet r=null;
		String sql="select * from Universities order by univname asc";
		db.open();
		PreparedStatement p=db.query_prep(sql);
		try {
			r=p.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<University> univlist = new ArrayList<University>();	
		try {
			while(r.next()){
				University univ=new University();
				univ.setUnivid(r.getString("univid"));
				univ.setUnivname(r.getString("univname"));
				univ.setUnivwebsite(r.getString("univwebsite"));
				
				univlist.add(univ);
			}
			db.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return univlist;
	}
	public String getUnivname(String univid){
		String univname=null;
		DBUtility db=new DBUtility();
		ResultSet r=null;
		String sql="select univname from Universities where univid=?";
		db.open();
		PreparedStatement p=db.query_prep(sql);
		try {
			p.setString(1, univid);
			r=p.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if(r.next()){
				univname=r.getString("univname");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.close();
		return univname;
	}
}
