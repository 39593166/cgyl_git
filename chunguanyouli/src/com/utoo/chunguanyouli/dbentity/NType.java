package com.utoo.chunguanyouli.dbentity;

/**
 * NType entity. @author MyEclipse Persistence Tools
 */

public class NType implements java.io.Serializable {

	// Fields

	private NTypeId id;

	// Constructors

	/** default constructor */
	public NType() {
	}

	/** full constructor */
	public NType(NTypeId id) {
		this.id = id;
	}

	// Property accessors

	public NTypeId getId() {
		return this.id;
	}

	public void setId(NTypeId id) {
		this.id = id;
	}

}