package com.utoo.chunguanyouli.entity;

import java.io.Serializable;

public class Commend implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8371412572358754938L;
	private int commendId;
	private String context;
	private User commender;
	private long commendTime;
	private float commendScore;
	public int getCommendId() {
		return commendId;
	}
	public void setCommendId(int commendId) {
		this.commendId = commendId;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public User getCommender() {
		return commender;
	}
	public void setCommender(User commender) {
		this.commender = commender;
	}
	public long getCommendTime() {
		return commendTime;
	}
	public void setCommendTime(long commendTime) {
		this.commendTime = commendTime;
	}
	public float getCommendScore() {
		return commendScore;
	}
	public void setCommendScore(float commendScore) {
		this.commendScore = commendScore;
	}
	
}
