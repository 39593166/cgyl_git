package com.utoo.chunguanyouli.dbentity;

/**
 * CgConcern entity. @author MyEclipse Persistence Tools
 */

public class CgConcern implements java.io.Serializable {

	// Fields

	private CgConcernId id;

	// Constructors

	/** default constructor */
	public CgConcern() {
	}

	/** full constructor */
	public CgConcern(CgConcernId id) {
		this.id = id;
	}

	// Property accessors

	public CgConcernId getId() {
		return this.id;
	}

	public void setId(CgConcernId id) {
		this.id = id;
	}

}