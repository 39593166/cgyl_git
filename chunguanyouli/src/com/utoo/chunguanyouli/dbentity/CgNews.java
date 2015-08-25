package com.utoo.chunguanyouli.dbentity;

/**
 * CgNews entity. @author MyEclipse Persistence Tools
 */

public class CgNews implements java.io.Serializable {

	// Fields

	private CgNewsId id;

	// Constructors

	/** default constructor */
	public CgNews() {
	}

	/** full constructor */
	public CgNews(CgNewsId id) {
		this.id = id;
	}

	// Property accessors

	public CgNewsId getId() {
		return this.id;
	}

	public void setId(CgNewsId id) {
		this.id = id;
	}

}