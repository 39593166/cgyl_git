package com.utoo.chunguanyouli.dbentity;

/**
 * GGoodsSetAttr entity. @author MyEclipse Persistence Tools
 */

public class GGoodsSetAttr implements java.io.Serializable {

	// Fields

	private GGoodsSetAttrId id;

	// Constructors

	/** default constructor */
	public GGoodsSetAttr() {
	}

	/** full constructor */
	public GGoodsSetAttr(GGoodsSetAttrId id) {
		this.id = id;
	}

	// Property accessors

	public GGoodsSetAttrId getId() {
		return this.id;
	}

	public void setId(GGoodsSetAttrId id) {
		this.id = id;
	}

}