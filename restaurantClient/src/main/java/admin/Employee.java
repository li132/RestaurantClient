package admin;

import java.io.Serializable;

public class Employee implements Serializable{
	private int empid;
	private String empname;
	private String empsex;
	private int empphone;
	private int emplevel;
	private String emppassword;
	
	/**
	 * 
	 */
	public Employee() {
		super();
	}

	/**
	 * @param empid
	 * @param empname
	 * @param empsex
	 * @param empphone
	 * @param emplevel
	 */
	public Employee(int empid, String empname, String empsex, int empphone, int emplevel, String emppassword) {
		super();
		this.empid = empid;
		this.empname = empname;
		this.empsex = empsex;
		this.empphone = empphone;
		this.emplevel = emplevel;
		this.emppassword = emppassword;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getEmpsex() {
		return empsex;
	}

	public void setEmpsex(String empsex) {
		this.empsex = empsex;
	}

	public int getEmpphone() {
		return empphone;
	}

	public void setEmpphone(int empphone) {
		this.empphone = empphone;
	}

	public int getEmplevel() {
		return emplevel;
	}

	public void setEmplevel(int emplevel) {
		this.emplevel = emplevel;
	}
	
	public String getEmppassword() {
		return emppassword;
	}

	public void setEmppassword(String emppassword) {
		this.emppassword = emppassword;
	}

	@Override
	public String toString() {
		
		return this.empid+"\t"+this.emppassword+"\t"+this.empname+"\t"+empsex+"\t"+empphone+"\t"+emplevel;
	}
}
