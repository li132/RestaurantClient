package admin;

import java.io.Serializable;

public class Salnum implements Serializable{
	private int eatid;
	private int salnum;
	
	/**
	 * 
	 */
	public Salnum() {
		super();
	}
	/**
	 * @param eatid
	 * @param salsum
	 */
	public Salnum(int eatid, int salnum) {
		super();
		this.eatid = eatid;
		this.salnum = salnum;
	}
	public int getEatid() {
		return eatid;
	}
	public void setEatid(int eatid) {
		this.eatid = eatid;
	}
	public int getSalnum() {
		return salnum;
	}
	public void setSalsum(int salnum) {
		this.salnum = salnum;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.eatid+"\t"+this.salnum;
	}
}
