package com.utoo.chunguanyouli.info;

import java.util.List;

public class FeatureProductInfo {

	private String state;

	private String sid;

	private String num;

	private List<Val> val;

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return this.state;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSid() {
		return this.sid;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getNum() {
		return this.num;
	}

	public List<Val> getVal() {
		return val;
	}

	public void setVal(List<Val> val) {
		this.val = val;
	}

	public class Val {
		private String id;
		private String username;
		private String userpass;
		private String realname;
		private String datereg;
		private String datelogin;
		private String datelastlogin;
		private String ip;
		private String iplast;
		private String islock;
		private String wrongnum;
		private String wrongdate;
		private String integral;
		private String photo;
		private String job;
		private String tid;
		private String numintegral;
		private String cid;

		public void setId(String id) {
			this.id = id;
		}

		public String getId() {
			return this.id;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getUsername() {
			return this.username;
		}

		public void setUserpass(String userpass) {
			this.userpass = userpass;
		}

		public String getUserpass() {
			return this.userpass;
		}

		public void setRealname(String realname) {
			this.realname = realname;
		}

		public String getRealname() {
			return this.realname;
		}

		public void setDatereg(String datereg) {
			this.datereg = datereg;
		}

		public String getDatereg() {
			return this.datereg;
		}

		public void setDatelogin(String datelogin) {
			this.datelogin = datelogin;
		}

		public String getDatelogin() {
			return this.datelogin;
		}

		public void setDatelastlogin(String datelastlogin) {
			this.datelastlogin = datelastlogin;
		}

		public String getDatelastlogin() {
			return this.datelastlogin;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public String getIp() {
			return this.ip;
		}

		public void setIplast(String iplast) {
			this.iplast = iplast;
		}

		public String getIplast() {
			return this.iplast;
		}

		public void setIslock(String islock) {
			this.islock = islock;
		}

		public String getIslock() {
			return this.islock;
		}

		public void setWrongnum(String wrongnum) {
			this.wrongnum = wrongnum;
		}

		public String getWrongnum() {
			return this.wrongnum;
		}

		public void setWrongdate(String wrongdate) {
			this.wrongdate = wrongdate;
		}

		public String getWrongdate() {
			return this.wrongdate;
		}

		public void setIntegral(String integral) {
			this.integral = integral;
		}

		public String getIntegral() {
			return this.integral;
		}

		public void setPhoto(String photo) {
			this.photo = photo;
		}

		public String getPhoto() {
			return this.photo;
		}

		public void setJob(String job) {
			this.job = job;
		}

		public String getJob() {
			return this.job;
		}

		public void setTid(String tid) {
			this.tid = tid;
		}

		public String getTid() {
			return this.tid;
		}

		public void setNumintegral(String numintegral) {
			this.numintegral = numintegral;
		}

		public String getNumintegral() {
			return this.numintegral;
		}

		public void setCid(String cid) {
			this.cid = cid;
		}

		public String getCid() {
			return this.cid;
		}
	}
}
