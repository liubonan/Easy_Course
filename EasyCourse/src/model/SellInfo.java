package model;

public class SellInfo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String seller;
	private Textbook tb;
	private double sellprice;
	private String condition;
	private String note;
	
	public SellInfo()
	{
		this.setCondition(null);
		this.setNote(null);
		this.setSeller(null);
		this.setSellprice(0);
		this.setTb(null);
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public Textbook getTb() {
		return tb;
	}
	public void setTb(Textbook tb) {
		this.tb = tb;
	}
	public double getSellprice() {
		return sellprice;
	}
	public void setSellprice(double sellprice) {
		this.sellprice = sellprice;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	

}
