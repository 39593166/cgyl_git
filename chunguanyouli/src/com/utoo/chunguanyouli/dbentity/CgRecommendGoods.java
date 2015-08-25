package com.utoo.chunguanyouli.dbentity;

/**
 * CgRecommendGoods entity. @author MyEclipse Persistence Tools
 */

public class CgRecommendGoods implements java.io.Serializable {

	// Fields

	private CgRecommendGoodsId id;

	// Constructors

	/** default constructor */
	public CgRecommendGoods() {
	}

	/** full constructor */
	public CgRecommendGoods(CgRecommendGoodsId id) {
		this.id = id;
	}

	// Property accessors

	public CgRecommendGoodsId getId() {
		return this.id;
	}

	public void setId(CgRecommendGoodsId id) {
		this.id = id;
	}

}