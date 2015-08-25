package com.utoo.chunguanyouli.dbentity;

import com.utoo.chunguanyouli.server.ClientConfigs;



/**
 * UUserInfoId entity. @author MyEclipse Persistence Tools
 */

public class UUserInfoId implements java.io.Serializable {

	private static final long serialVersionUID = -8014159113458670466L;
	private int id;
	private String username;
	private String userpass;
	private String realname;
	private String datereg;
	private String datelogin;
	private String datelastlogin;
	private String ip;
	private String iplast;
	private boolean islock;
	private int wrongnum;
	private String wrongdate;
	private int integral;
	private String photo;
	private String job;
	private int tid;
	private int numintegral;
	private int cid;

	//
	private String sid;
	// Constructors

	/** default constructor */
	public UUserInfoId() {
	}



	// Property accessors

	public String getDatereg() {
		return datereg;
	}



	public void setDatereg(String datereg) {
		this.datereg = datereg;
	}



	public String getDatelogin() {
		return datelogin;
	}



	public void setDatelogin(String datelogin) {
		this.datelogin = datelogin;
	}



	public String getDatelastlogin() {
		return datelastlogin;
	}



	public void setDatelastlogin(String datelastlogin) {
		this.datelastlogin = datelastlogin;
	}



	public String getWrongdate() {
		return wrongdate;
	}



	public void setWrongdate(String wrongdate) {
		this.wrongdate = wrongdate;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return this.userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}


	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIplast() {
		return this.iplast;
	}

	public void setIplast(String iplast) {
		this.iplast = iplast;
	}

	public boolean getIslock() {
		return this.islock;
	}

	public void setIslock(boolean islock) {
		this.islock = islock;
	}

	public int getWrongnum() {
		return this.wrongnum;
	}

	public void setWrongnum(int wrongnum) {
		this.wrongnum = wrongnum;
	}


	public int getIntegral() {
		return this.integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public String getPhoto() {
		if (photo.startsWith("http"))
			return photo;
		else
			return ClientConfigs.PICHOST + photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getTid() {
		return this.tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getNumintegral() {
		return this.numintegral;
	}

	public void setNumintegral(int numintegral) {
		this.numintegral = numintegral;
	}

	public int getCid() {
		return this.cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}


	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

}