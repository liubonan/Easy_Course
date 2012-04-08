package database;

import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtility {
	
	private final String url = "jdbc:oracle:thin:@w4111b.cs.columbia.edu:1521:ADB";
	private final String driver = "oracle.jdbc.driver.OracleDriver";
	private final String username = "bl2432";	 
	private final String password = "admin";
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet result = null;
	private PreparedStatement prepstmt = null;
	
	public DBUtility (){}
	
	public void open(){
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,password);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void close(){
		try {			
			
			if(conn != null){
				if(!conn.isClosed())
					conn.close();
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet query(String sql){		
		
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(sql);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
			
		return result;
	}
	
	public PreparedStatement query_prep(String sql){
		
		try {
			return conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public void edit(String sql){
		try {
			stmt = conn.createStatement();
			stmt.executeQuery(sql);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PreparedStatement edit_prep(String sql){
		try {
			prepstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prepstmt;
	}
	
	public String ClobReader(Clob clob)
	{
		Reader inStream;
		String data = null;
		
		if(clob == null)
			return "N/A";
		else
		{			
			try {
				inStream = clob.getCharacterStream();
				char[] c = new char[(int) clob.length()];
			    inStream.read(c);	     
				data = new String(c);
				inStream.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			return data;
		}
	}
	

}
