package admin;

import java.io.Serializable;

public class Vip implements Serializable{
	private int vipid;
	private int curid;
	private int viplevel;
	private int vipstate;
	private double vipdiscount;
	private double vipdalance;
	
	/**
	 * 
	 */
	public Vip() {
		super();
	}
	
	/**
	 * @param vipid
	 * @param curid
	 * @param viplevel
	 * @param vipstate
	 * @param vipdiscount
	 * @param vipdalance
	 */
	public Vip( int vipid,int curid, int viplevel, int vipstate, double vipdiscount, double vipdalance) {
		super();
		this.vipid = vipid;
		this.curid = curid;
		this.viplevel = viplevel;
		this.vipstate = vipstate;
		this.vipdiscount = vipdiscount;
		this.vipdalance = vipdalance;
	}

	public int getVipid() {
		return vipid;
	}

	public void setVipid(int vipid) {
		this.vipid = vipid;
	}

	public int getCurid() {
		return curid;
	}

	public void setCurid(int curid) {
		this.curid = curid;
	}

	public int getViplevel() {
		return viplevel;
	}

	public void setViplevel(int viplevel) {
		this.viplevel = viplevel;
	}

	public int getVipstate() {
		return vipstate;
	}

	public void setVipstate(int vipstate) {
		this.vipstate = vipstate;
	}

	public double getVipdiscount() {
		return vipdiscount;
	}

	public void setVipdiscount(double vipdiscount) {
		this.vipdiscount = vipdiscount;
	}

	public double getVipdalance() {
		return vipdalance;
	}

	public void setVipdalance(double vipdalance) {
		this.vipdalance = vipdalance;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.vipid+"\t"+this.curid+"\t"+this.viplevel+"\t"+this.vipstate+"\t"+this.vipdiscount+"\t"+this.vipdalance;
	}
}
