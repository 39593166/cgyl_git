package com.utoo.chunguanyouli.dbentity;

/**
 * CgAddress entity. @author MyEclipse Persistence Tools
 */

public class CgAddress implements java.io.Serializable {

	// Fields

	private CgAddressId id;

	// Constructors

	/** default constructor */
	public CgAddress() {
	}

	/** full constructor */
	public CgAddress(CgAddressId id) {
		this.id = id;
	}

	// Property accessors

	public CgAddressId getId() {
		return this.id;
	}

	public void setId(CgAddressId id) {
		this.id = id;
	}

}