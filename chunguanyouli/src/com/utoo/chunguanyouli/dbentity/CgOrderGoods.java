package com.utoo.chunguanyouli.dbentity;

/**
 * CgOrderGoods entity. @author MyEclipse Persistence Tools
 */

public class CgOrderGoods implements java.io.Serializable {

	// Fields

	private CgOrderGoodsId id;

	// Constructors

	/** default constructor */
	public CgOrderGoods() {
	}

	/** full constructor */
	public CgOrderGoods(CgOrderGoodsId id) {
		this.id = id;
	}

	// Property accessors

	public CgOrderGoodsId getId() {
		return this.id;
	}

	public void setId(CgOrderGoodsId id) {
		this.id = id;
	}

}