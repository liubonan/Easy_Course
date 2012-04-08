package utility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DBUtility;
import model.Textbook;

public class BookHelper {
	public BookHelper(){
		
	}
	
	public List<Textbook> getBookList(String courseid){
		DBUtility db=new DBUtility();
		ResultSet r=null;
		String sql="select * from Textbooks where tbISBN NOT IN (select tbISBN from CoursesRequireTextbooks where cid=?) order by tbtitle asc";
		db.open();
		PreparedStatement p=db.query_prep(sql);
		try {
			p.setString(1, courseid);
			r=p.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Textbook> booklist=new ArrayList<Textbook>();

		try {
			while(r.next()){
				Textbook book=new Textbook();
				book.setTbISBN(r.getString("tbISBN"));
				book.setTbtitle(r.getString("tbtitle"));
				book.setTbauthor(r.getString("tbauthor"));
				book.setTbpublisher(r.getString("tbpulisher"));
				book.setTbprice(r.getDouble("tbprice"));
				
				booklist.add(book);
			}
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return booklist;
	}
	
}
