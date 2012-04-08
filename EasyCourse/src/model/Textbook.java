package model;

public class Textbook implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tbISBN;
	private String tbtitle;
	private String tbauthor;
	private String tbpublisher;
	private double tbprice;
	
	public Textbook(){
		this.setTbauthor(null);
		this.setTbISBN(null);
		this.setTbprice(0.0);
		this.setTbpublisher(null);
		this.setTbtitle(null);		
	}
	
	public String getTbISBN(){
		return tbISBN;
	}
	public void setTbISBN(String tbISBN){
		this.tbISBN=tbISBN;
	}
	
	public String getTbtitle(){
		return tbtitle;
	}
	public void setTbtitle(String tbtitle){
		this.tbtitle=tbtitle;
	}
	
	public String getTbauthor(){
		return tbauthor;
	}
	public void setTbauthor(String tbauthor){
		this.tbauthor=tbauthor;
	}
	
	public String getTbpublisher(){
		return tbpublisher;
	}
	public void setTbpublisher(String tbpublisher){
		this.tbpublisher=tbpublisher;
	}
	
	public double getTbprice(){
		return tbprice;
	}
	public void setTbprice(double tbprice){
		this.tbprice=tbprice;
	}
}
