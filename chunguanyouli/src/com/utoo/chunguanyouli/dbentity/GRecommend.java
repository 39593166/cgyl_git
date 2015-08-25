package com.utoo.chunguanyouli.dbentity;

/**
 * GRecommend entity. @author MyEclipse Persistence Tools
 */

public class GRecommend implements java.io.Serializable {

	// Fields

	private GRecommendId id;

	// Constructors

	/** default constructor */
	public GRecommend() {
	}

	/** full constructor */
	public GRecommend(GRecommendId id) {
		this.id = id;
	}

	// Property accessors

	public GRecommendId getId() {
		return this.id;
	}

	public void setId(GRecommendId id) {
		this.id = id;
	}

}