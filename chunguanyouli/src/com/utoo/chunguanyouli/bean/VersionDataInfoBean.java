package com.utoo.chunguanyouli.bean;

import java.util.List;

public class VersionDataInfoBean {
	// "state":1,"sid":"","num":"1"
	private int state;

	private String sid;

	private int num;

	private List<VersionInfo> val;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public List<VersionInfo> getVal() {
		return val;
	}

	public void setVal(List<VersionInfo> val) {
		this.val = val;
	}

	public VersionInfo getVersionInfo() {
		if (val != null && val.size() > 0) {
			return val.get(0);
		}
		return null;
	}

	
}
