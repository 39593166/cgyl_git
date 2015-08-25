package com.utoo.chunguanyouli.dbentity;

/**
 * CgUser entity. @author MyEclipse Persistence Tools
 */

public class CgUser implements java.io.Serializable {

	// Fields

	private CgUserId id;

	// Constructors

	/** default constructor */
	public CgUser() {
	}

	/** full constructor */
	public CgUser(CgUserId id) {
		this.id = id;
	}

	// Property accessors

	public CgUserId getId() {
		return this.id;
	}

	public void setId(CgUserId id) {
		this.id = id;
	}

}