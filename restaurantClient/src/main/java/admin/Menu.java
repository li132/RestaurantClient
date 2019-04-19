package admin;

import java.io.Serializable;

public class Menu implements Serializable{
	private int eatid;
	private String eatname;
	private int eatprice;
	private String typename;
	/**
	 * 
	 */
	public Menu() {
		super();
	}


	/**
	 * @param eatid
	 * @param eatname
	 * @param eatprice
	 * @param typename
	 */
	public Menu(int eatid, String eatname, int eatprice, String typename) {
		super();
		this.eatid = eatid;
		this.eatname = eatname;
		this.eatprice = eatprice;
		this.typename = typename;
	}


	public int getEatid() {
		return eatid;
	}


	public void setEatid(int eatid) {
		this.eatid = eatid;
	}


	public String getEatname() {
		return eatname;
	}


	public void setEatname(String eatname) {
		this.eatname = eatname;
	}


	public int getEatprice() {
		return eatprice;
	}


	public void setEatprice(int eatprice) {
		this.eatprice = eatprice;
	}


	public String getTypename() {
		return typename;
	}


	public void setTypename(String typename) {
		this.typename = typename;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.eatid+"\t"+this.eatname+"\t"+this.typename+"\t"+this.eatprice;
	}
}
