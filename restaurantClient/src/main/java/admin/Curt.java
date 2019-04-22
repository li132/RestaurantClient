package admin;

import java.io.Serializable;

public class Curt implements Serializable{
	private int ticketid;
	private int eatid;
	private int eatnum;
	
	/**
	 * 
	 */
	public Curt() {
		super();
	}

	/**
	 * @param ticketid
	 * @param eatid
	 * @param eatnum
	 */
	public Curt(int ticketid, int eatid, int eatnum) {
		super();
		this.ticketid = ticketid;
		this.eatid = eatid;
		this.eatnum = eatnum;
	}

	/**
	 * @param eatid
	 * @param eatnum
	 */
	public Curt(int eatid, int eatnum) {
		super();
		this.eatid = eatid;
		this.eatnum = eatnum;
	}

	public int getTicketid() {
		return ticketid;
	}

	public void setTicketid(int ticketid) {
		this.ticketid = ticketid;
	}

	public int getEatid() {
		return eatid;
	}

	public void setEatid(int eatid) {
		this.eatid = eatid;
	}

	public int getEatnum() {
		return eatnum;
	}

	public void setEatnum(int eatnum) {
		this.eatnum = eatnum;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.ticketid+"\t"+this.eatid+"\t"+this.eatnum;
	}
}
