package model;

public class Course implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cid;
	private String cname;
	private String cdescription;
	private String semester;
	
	public Course(){
		this.setCid(null);
		this.setCdescription(null);
		this.setCname(null);
	}
	
	public String getCid(){
		return cid;
	}
	public void setCid(String cid){
		this.cid=cid;
	}
	
	public String getCname(){
		return cname;
	}
	public void setCname(String cname){
		this.cname=cname;
	}
	
	public String getCdescription(){
		return cdescription;
	}
	public void setCdescription(String cdescription){
		this.cdescription=cdescription;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}
}
