package com.utoo.chunguanyouli.dbentity;

/**
 * OGoodsAttr entity. @author MyEclipse Persistence Tools
 */

public class OGoodsAttr implements java.io.Serializable {

	// Fields

	private OGoodsAttrId id;

	// Constructors

	/** default constructor */
	public OGoodsAttr() {
	}

	/** full constructor */
	public OGoodsAttr(OGoodsAttrId id) {
		this.id = id;
	}

	// Property accessors

	public OGoodsAttrId getId() {
		return this.id;
	}

	public void setId(OGoodsAttrId id) {
		this.id = id;
	}

}