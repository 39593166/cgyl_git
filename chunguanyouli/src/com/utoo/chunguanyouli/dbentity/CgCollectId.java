package com.utoo.chunguanyouli.dbentity;

/**
 * CgCollectId entity. @author MyEclipse Persistence Tools
 */

public class CgCollectId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2376125792142995579L;
	private int id;
	private int uid;
	private String datesend;
	private int gid;
	private int tid;//0商品，1村子

	// Constructors

	/** default constructor */
	public CgCollectId() {
	}

	/** minimal constructor */
	public CgCollectId(int id) {
		this.id = id;
	}

	/** full constructor */
	public CgCollectId(int id, int uid, String datesend, int gid, int tid) {
		this.id = id;
		this.uid = uid;
		this.datesend = datesend;
		this.gid = gid;
		this.tid = tid;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getDatesend() {
		return this.datesend;
	}

	public void setDatesend(String datesend) {
		this.datesend = datesend;
	}

	public int getGid() {
		return this.gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public int getTid() {
		return this.tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

}