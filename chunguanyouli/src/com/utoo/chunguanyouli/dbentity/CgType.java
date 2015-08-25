package com.utoo.chunguanyouli.dbentity;

/**
 * CgType entity. @author MyEclipse Persistence Tools
 */

public class CgType implements java.io.Serializable {

	// Fields

	private CgTypeId id;

	// Constructors

	/** default constructor */
	public CgType() {
	}

	/** full constructor */
	public CgType(CgTypeId id) {
		this.id = id;
	}

	// Property accessors

	public CgTypeId getId() {
		return this.id;
	}

	public void setId(CgTypeId id) {
		this.id = id;
	}

}