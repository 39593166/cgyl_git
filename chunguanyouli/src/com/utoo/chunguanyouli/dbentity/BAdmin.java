package com.utoo.chunguanyouli.dbentity;

/**
 * BAdmin entity. @author MyEclipse Persistence Tools
 */

public class BAdmin implements java.io.Serializable {

	// Fields

	private BAdminId id;

	// Constructors

	/** default constructor */
	public BAdmin() {
	}

	/** full constructor */
	public BAdmin(BAdminId id) {
		this.id = id;
	}

	// Property accessors

	public BAdminId getId() {
		return this.id;
	}

	public void setId(BAdminId id) {
		this.id = id;
	}

}