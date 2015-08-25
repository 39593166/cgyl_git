package com.utoo.chunguanyouli.dbentity;

import java.io.Serializable;

import com.utoo.chunguanyouli.server.ClientConfigs;

public class Pics implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6711227516464077042L;
	private int id;
	private int gid;
	private int tid;
	private String url;

	public Pics() {
		super();
	}

	public Pics(int gid, int tid, String url) {
		super();
		this.gid = gid;
		this.tid = tid;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getUrl() {
		if (url.startsWith("http") || url.startsWith("/storage")) {
			return url;
		} else {
			return ClientConfigs.PICHOST + url;

		}
	}
	public String getOUrl(){
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
