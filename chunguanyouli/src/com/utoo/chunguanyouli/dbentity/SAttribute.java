package com.utoo.chunguanyouli.dbentity;

/**
 * SAttribute entity. @author MyEclipse Persistence Tools
 */

public class SAttribute implements java.io.Serializable {

	// Fields

	private SAttributeId id;

	// Constructors

	/** default constructor */
	public SAttribute() {
	}

	/** full constructor */
	public SAttribute(SAttributeId id) {
		this.id = id;
	}

	// Property accessors

	public SAttributeId getId() {
		return this.id;
	}

	public void setId(SAttributeId id) {
		this.id = id;
	}

}