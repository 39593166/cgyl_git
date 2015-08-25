package com.utoo.chunguanyouli.dbentity;

/**
 * CgCollect entity. @author MyEclipse Persistence Tools
 */

public class CgCollect implements java.io.Serializable {

	// Fields

	private CgCollectId id;

	// Constructors

	/** default constructor */
	public CgCollect() {
	}

	/** full constructor */
	public CgCollect(CgCollectId id) {
		this.id = id;
	}

	// Property accessors

	public CgCollectId getId() {
		return this.id;
	}

	public void setId(CgCollectId id) {
		this.id = id;
	}

}