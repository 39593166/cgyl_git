package com.utoo.chunguanyouli.dbentity;

/**
 * CgGoods entity. @author MyEclipse Persistence Tools
 */

public class CgGoods implements java.io.Serializable {

	// Fields

	private CgGoodsId id;

	// Constructors

	/** default constructor */
	public CgGoods() {
	}

	/** full constructor */
	public CgGoods(CgGoodsId id) {
		this.id = id;
	}

	// Property accessors

	public CgGoodsId getId() {
		return this.id;
	}

	public void setId(CgGoodsId id) {
		this.id = id;
	}

}