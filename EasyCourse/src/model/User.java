package model;

public class User implements java.io.Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userid;
	private String uemail;
	private String unickname;
	private String upassword;
	private String univid;
	
	public User(){
		this.setUemail(null);
		this.setUnickname(null);
		this.setUpassword(null);
		this.setUserid(null);
		this.setUnivid(null);
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public String getUnickname() {
		return unickname;
	}

	public void setUnickname(String unickname) {
		this.unickname = unickname;
	}

	public String getUpassword() {
		return upassword;
	}

	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	
	public String getUnivid(){
		return univid;
	}
	public void setUnivid(String univid){
		this.univid=univid;
	}
	

}
