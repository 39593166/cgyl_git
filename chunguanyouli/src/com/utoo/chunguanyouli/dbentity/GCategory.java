package com.utoo.chunguanyouli.dbentity;

/**
 * GCategory entity. @author MyEclipse Persistence Tools
 */

public class GCategory implements java.io.Serializable {

	// Fields

	private GCategoryId id;

	// Constructors

	/** default constructor */
	public GCategory() {
	}

	/** full constructor */
	public GCategory(GCategoryId id) {
		this.id = id;
	}

	// Property accessors

	public GCategoryId getId() {
		return this.id;
	}

	public void setId(GCategoryId id) {
		this.id = id;
	}

}