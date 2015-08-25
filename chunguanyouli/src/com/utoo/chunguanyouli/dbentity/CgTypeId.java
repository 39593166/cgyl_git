package com.utoo.chunguanyouli.dbentity;

import java.io.Serializable;

import com.utoo.chunguanyouli.server.ClientConfigs;

/**
 * CgTypeId entity. @author MyEclipse Persistence Tools
 */

public class CgTypeId implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5582360912688358711L;
	private int id;
	private String name;
	private int pid;
	private int tid;
	private String pic;

	// Constructors

	/** default constructor */
	public CgTypeId() {
	}

	/** minimal constructor */
	public CgTypeId(int id) {
		this.id = id;
	}

	/** full constructor */
	public CgTypeId(int id, String name, int pid, int tid) {
		this.id = id;
		this.name = name;
		this.pid = pid;
		this.tid = tid;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPid() {
		return this.pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getTid() {
		return this.tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getPic() {
		if (pic.startsWith("http")) {
			return pic;
		} else {
			return ClientConfigs.PICHOST + pic;
		}
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

}