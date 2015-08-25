package com.utoo.chunguanyouli.dbentity;

/**
 * OGoods entity. @author MyEclipse Persistence Tools
 */

public class OGoods implements java.io.Serializable {

	// Fields

	private OGoodsId id;

	// Constructors

	/** default constructor */
	public OGoods() {
	}

	/** full constructor */
	public OGoods(OGoodsId id) {
		this.id = id;
	}

	// Property accessors

	public OGoodsId getId() {
		return this.id;
	}

	public void setId(OGoodsId id) {
		this.id = id;
	}

}