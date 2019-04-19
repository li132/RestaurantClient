package admin;

import java.io.Serializable;

public class Type implements Serializable{
	private int typeid;
	private String typename;
	
	/**
	 * 
	 */
	public Type() {
		super();
	}

	/**
	 * @param typeid
	 * @param typename
	 */
	public Type(int typeid, String typename) {
		super();
		this.typeid = typeid;
		this.typename = typename;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
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
		return this.typeid+"\t"+this.typename;
	}
}
