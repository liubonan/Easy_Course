package model;

public class Teachingstaff implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tsid;
	private String tsname;
	private String tstitle;
	
	public Teachingstaff(){
		this.setTsid(null);
		this.setTsname(null);
		this.setTstitle(null);
	}
	
	public String getTsid(){
		return tsid;
	}
	public void setTsid(String tsid){
		this.tsid=tsid;
	}
	
	public String getTsname(){
		return tsname;
	}
	public void setTsname(String tsname){
		this.tsname=tsname;
	}
	
	public String getTstitle(){
		return tstitle;
	}
	public void setTstitle(String tstitle){
		this.tstitle=tstitle;
	}

}
