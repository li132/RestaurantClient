package admin;

import java.io.Serializable;

public class Customer implements Serializable{
	private int curid;
	private String curname;
	private String cuesex;
	private int curphone;
	
	/**
	 * 
	 */
	public Customer() {
		super();
	}
	/**
	 * @param curid
	 * @param curname
	 * @param cuesex
	 * @param curphone
	 */
	public Customer(int curid, String curname, String cuesex, int curphone) {
		super();
		this.curid = curid;
		this.curname = curname;
		this.cuesex = cuesex;
		this.curphone = curphone;
	}
	public int getCurid() {
		return curid;
	}
	public void setCurid(int curid) {
		this.curid = curid;
	}
	public String getCurname() {
		return curname;
	}
	public void setCurname(String curname) {
		this.curname = curname;
	}
	public String getCuesex() {
		return cuesex;
	}
	public void setCuesex(String cuesex) {
		this.cuesex = cuesex;
	}
	public int getCurphone() {
		return curphone;
	}
	public void setCurphone(int curphone) {
		this.curphone = curphone;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.curid+"\t"+this.curname+"\t"+this.cuesex+"\t"+this.curphone;
	}
}
