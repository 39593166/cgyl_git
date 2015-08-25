package com.utoo.chunguanyouli.dbentity;

/**
 * UGoodsCollect entity. @author MyEclipse Persistence Tools
 */

public class UGoodsCollect implements java.io.Serializable {

	// Fields

	private UGoodsCollectId id;

	// Constructors

	/** default constructor */
	public UGoodsCollect() {
	}

	/** full constructor */
	public UGoodsCollect(UGoodsCollectId id) {
		this.id = id;
	}

	// Property accessors

	public UGoodsCollectId getId() {
		return this.id;
	}

	public void setId(UGoodsCollectId id) {
		this.id = id;
	}

}