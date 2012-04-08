package model;


import java.sql.Timestamp;


public class TextbookComment implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bcid;
	private String bctitle;
	private String bccomment;
	private String tbISBN;
	private Timestamp bctime;
	private String userid;
	private String unickname;
	
	public TextbookComment(){
		this.setBccomment(null);
		this.setBcid(null);
		this.setBctime(null);
		this.setBctitle(null);
		this.setTbISBN(null);
		this.setUnickname(null);
		this.setUserid(null);
	}
	
	public String getBcid(){
		return bcid;
	}
	public void setBcid(String bcid){
		this.bcid=bcid;
	}
	
	public String getBctitle(){
		return bctitle;
	}
	public void setBctitle(String bctitle){
		this.bctitle=bctitle;
	}
	
	public String getBccomment(){
		return bccomment;
	}	
	
	public void setBccomment(String bccomment){		
		this.bccomment=bccomment;
	}
	
	public String getTbISBN(){
		return tbISBN;
	}
	public void setTbISBN(String tbISBN){
		this.tbISBN=tbISBN;
	}
	
	public Timestamp getBctime(){
		return bctime;
	}
	public void setBctime(Timestamp bctime){
		this.bctime=bctime;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUnickname() {
		return unickname;
	}

	public void setUnickname(String unickname) {
		this.unickname = unickname;
	}
	
}
