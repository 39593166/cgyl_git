package com.utoo.chunguanyouli.dbentity;

/**
 * GCategoryAttr entity. @author MyEclipse Persistence Tools
 */

public class GCategoryAttr implements java.io.Serializable {

	// Fields

	private GCategoryAttrId id;

	// Constructors

	/** default constructor */
	public GCategoryAttr() {
	}

	/** full constructor */
	public GCategoryAttr(GCategoryAttrId id) {
		this.id = id;
	}

	// Property accessors

	public GCategoryAttrId getId() {
		return this.id;
	}

	public void setId(GCategoryAttrId id) {
		this.id = id;
	}

}