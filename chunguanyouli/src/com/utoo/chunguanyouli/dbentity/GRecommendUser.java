package com.utoo.chunguanyouli.dbentity;

/**
 * GRecommendUser entity. @author MyEclipse Persistence Tools
 */

public class GRecommendUser implements java.io.Serializable {

	// Fields

	private GRecommendUserId id;

	// Constructors

	/** default constructor */
	public GRecommendUser() {
	}

	/** full constructor */
	public GRecommendUser(GRecommendUserId id) {
		this.id = id;
	}

	// Property accessors

	public GRecommendUserId getId() {
		return this.id;
	}

	public void setId(GRecommendUserId id) {
		this.id = id;
	}

}