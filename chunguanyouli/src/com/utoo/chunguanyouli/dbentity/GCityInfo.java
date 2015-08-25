package com.utoo.chunguanyouli.dbentity;

/**
 * GCityInfo entity. @author MyEclipse Persistence Tools
 */

public class GCityInfo implements java.io.Serializable {

	// Fields

	private GCityInfoId id;

	// Constructors

	/** default constructor */
	public GCityInfo() {
	}

	/** full constructor */
	public GCityInfo(GCityInfoId id) {
		this.id = id;
	}

	// Property accessors

	public GCityInfoId getId() {
		return this.id;
	}

	public void setId(GCityInfoId id) {
		this.id = id;
	}

}