package com.utoo.chunguanyouli.server;

import java.io.Serializable;
import java.util.ArrayList;

public class ResponseParam<T> implements Serializable {
	private static final long serialVersionUID = 8434307408445687761L;
	private int state = 0;
	private int num = 0;
	private String sid;
	private ArrayList<T> val;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public ArrayList<T> getVal() {
		return val;
	}

	public void setVal(ArrayList<T> val) {
		this.val = val;
	}

}
