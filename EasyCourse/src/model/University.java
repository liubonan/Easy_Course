package model;

public class University implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String univid;
	private String univname;
	private String univwebsite;
	
	public University(){
		this.setUnivid(null);
		this.setUnivname(null);
		this.setUnivwebsite(null);
	}
	
	public String getUnivid(){
		return univid;
	}
	public void setUnivid(String univid){
		this.univid=univid;
	}
	
	public String getUnivname(){
		return univname;
	}
	public void setUnivname(String univname){
		this.univname=univname;
	}
	
	public String getUnivwebsite(){
		return univwebsite;
	}
	public void setUnivwebsite(String univwebsite){
		this.univwebsite=univwebsite;
	}
}
