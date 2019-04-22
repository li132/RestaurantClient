package admin;

import java.io.Serializable;

public class Menus implements Serializable{
	private int eatid;
	private String eatname;
	private int typeid;
	private int eatlevel;
	private int eatstock;
	private double eatprice;
	
	/**
	 * 
	 */
	public Menus() {
		super();
	}
	/**
	 * @param eatid
	 * @param eatname
	 * @param typeid
	 * @param eatlevel
	 * @param eatstock
	 * @param eatprice
	 */
	public Menus(int eatid, String eatname, int typeid, int eatlevel, int eatstock, double eatprice) {
		super();
		this.eatid = eatid;
		this.eatname = eatname;
		this.typeid = typeid;
		this.eatlevel = eatlevel;
		this.eatstock = eatstock;
		this.eatprice = eatprice;
	}


	/**
	 * @param eatname
	 * @param typeid
	 * @param eatlevel
	 * @param eatstock
	 * @param eatprice
	 */
	public Menus(String eatname, int typeid, int eatlevel, int eatstock, double eatprice) {
		super();
		this.eatname = eatname;
		this.typeid = typeid;
		this.eatlevel = eatlevel;
		this.eatstock = eatstock;
		this.eatprice = eatprice;
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
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public int getEatlevel() {
		return eatlevel;
	}
	public void setEatlevel(int eatlevel) {
		this.eatlevel = eatlevel;
	}
	public int getEatstock() {
		return eatstock;
	}
	public void setEatstock(int eatstock) {
		this.eatstock = eatstock;
	}
	public double getEatprice() {
		return eatprice;
	}
	public void setEatprice(double eatprice) {
		this.eatprice = eatprice;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.eatid+"\t"+this.eatname+"\t"+this.typeid+"\t"+this.eatlevel+"\t"+this.eatstock+"\t"+this.eatprice;
	}
}
