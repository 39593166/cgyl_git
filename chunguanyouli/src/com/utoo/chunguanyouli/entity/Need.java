package com.utoo.chunguanyouli.entity;

import java.io.Serializable;

public class Need implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6054478459823185270L;
	private int needId;
	private String name;

	public int getNeedId() {
		return needId;
	}

	public void setNeedId(int needId) {
		this.needId = needId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
