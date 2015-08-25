package com.utoo.chunguanyouli.dbentity;

/**
 * GBrand entity. @author MyEclipse Persistence Tools
 */

public class GBrand implements java.io.Serializable {

	// Fields

	private GBrandId id;

	// Constructors

	/** default constructor */
	public GBrand() {
	}

	/** full constructor */
	public GBrand(GBrandId id) {
		this.id = id;
	}

	// Property accessors

	public GBrandId getId() {
		return this.id;
	}

	public void setId(GBrandId id) {
		this.id = id;
	}

}