package com.utoo.chunguanyouli.dbentity;

/**
 * GFactory entity. @author MyEclipse Persistence Tools
 */

public class GFactory implements java.io.Serializable {

	// Fields

	private GFactoryId id;

	// Constructors

	/** default constructor */
	public GFactory() {
	}

	/** full constructor */
	public GFactory(GFactoryId id) {
		this.id = id;
	}

	// Property accessors

	public GFactoryId getId() {
		return this.id;
	}

	public void setId(GFactoryId id) {
		this.id = id;
	}

}