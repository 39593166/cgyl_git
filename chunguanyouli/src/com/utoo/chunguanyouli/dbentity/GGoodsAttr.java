package com.utoo.chunguanyouli.dbentity;

/**
 * GGoodsAttr entity. @author MyEclipse Persistence Tools
 */

public class GGoodsAttr implements java.io.Serializable {

	// Fields

	private GGoodsAttrId id;

	// Constructors

	/** default constructor */
	public GGoodsAttr() {
	}

	/** full constructor */
	public GGoodsAttr(GGoodsAttrId id) {
		this.id = id;
	}

	// Property accessors

	public GGoodsAttrId getId() {
		return this.id;
	}

	public void setId(GGoodsAttrId id) {
		this.id = id;
	}

}