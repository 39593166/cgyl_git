package com.utoo.chunguanyouli.dbentity;

/**
 * CgOrder entity. @author MyEclipse Persistence Tools
 */

public class CgOrder implements java.io.Serializable {

	// Fields

	private CgOrderId id;

	// Constructors

	/** default constructor */
	public CgOrder() {
	}

	/** full constructor */
	public CgOrder(CgOrderId id) {
		this.id = id;
	}

	// Property accessors

	public CgOrderId getId() {
		return this.id;
	}

	public void setId(CgOrderId id) {
		this.id = id;
	}

}