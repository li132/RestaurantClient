package admin;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Ticket implements Serializable{
	//订单编号
	private int id;
	private int empid;
	private Date date;
	//打印编号
	private String uuid;
	private Integer vipid;
	
	/**
	 * 
	 */
	public Ticket() {
		super();
	}
	/**
	 * @param id
	 * @param empid
	 * @param date
	 * @param uuid
	 * @param vipid
	 */
	public Ticket(int id, int empid, Date date, String uuid, Integer vipid) {
		super();
		this.id = id;
		this.empid = empid;
		this.date = date;
		this.uuid = uuid;
		this.vipid = vipid;
	}

	/**
	 * @param id
	 * @param empid
	 * @param date
	 * @param uuid
	 */
	public Ticket(int id, int empid, Date date, String uuid) {
		super();
		this.id = id;
		this.empid = empid;
		this.date = date;
		this.uuid = uuid;
	}
	
	
	/**
	 * @param empid
	 * @param date
	 * @param uuid
	 * @param vipid
	 */
	public Ticket(int empid, Date date, String uuid, Integer vipid) {
		super();
		this.empid = empid;
		this.date = date;
		this.uuid = uuid;
		this.vipid = vipid;
	}
	
	/**
	 * @param empid
	 * @param date
	 * @param uuid
	 */
	public Ticket(int empid, Date date, String uuid) {
		super();
		this.empid = empid;
		this.date = date;
		this.uuid = uuid;
	}
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getEmpid() {
		return empid;
	}



	public void setEmpid(int empid) {
		this.empid = empid;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public String getUuid() {
		return uuid;
	}



	public void setUuid(String uuid) {
		this.uuid = uuid;
	}



	public Integer getVipid() {
		return vipid;
	}



	public void setVipid(Integer vipid) {
		this.vipid = vipid;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.uuid+"\t"+this.empid+"\t"+this.date;
	}
}
