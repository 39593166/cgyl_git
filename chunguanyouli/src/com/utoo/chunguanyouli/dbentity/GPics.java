package com.utoo.chunguanyouli.dbentity;

/**
 * GPics entity. @author MyEclipse Persistence Tools
 */

public class GPics implements java.io.Serializable {

	// Fields

	private GPicsId id;

	// Constructors

	/** default constructor */
	public GPics() {
	}

	/** full constructor */
	public GPics(GPicsId id) {
		this.id = id;
	}

	// Property accessors

	public GPicsId getId() {
		return this.id;
	}

	public void setId(GPicsId id) {
		this.id = id;
	}

}