package model;


import java.sql.Timestamp;



public class TeachingstaffComment implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String scid;
	private String sctitle;
	private String sccomment;
	private String userid;
	private String unickname;
	private String tsid;
	private Timestamp sctime;
	
	public TeachingstaffComment(){
		this.setSccomment(null);
		this.setScid(null);
		this.setSctitle(null);
		this.setTsid(null);
		this.setUserid(null);
		this.setSctime(null);		
	}
	
	public String getScid(){
		return scid;
	}
	public void setScid(String scid){
		this.scid=scid;
	}
	
	public String getSctitle(){
		return sctitle;
	}
	public void setSctitle(String sctitle){
		this.sctitle=sctitle;
	}
	

	
	public String getUserid(){
		return userid;
	}
	public void setUserid(String userid){
		this.userid=userid;
	}
	
	public String getTsid(){
		return tsid;
	}
	public void setTsid(String tsid){
		this.tsid=tsid;
	}

	public Timestamp getSctime() {
		return sctime;
	}

	public void setSctime(Timestamp sctime) {
		this.sctime = sctime;
	}

	public String getSccomment() {
		return sccomment;
	}

	public void setSccomment(String sccomment) {
		this.sccomment = sccomment;
	}

	public String getUnickname() {
		return unickname;
	}

	public void setUnickname(String unickname) {
		this.unickname = unickname;
	}
}
