package com.utoo.chunguanyouli.dbentity;

/**
 * CgCity entity. @author MyEclipse Persistence Tools
 */

public class CgCity implements java.io.Serializable {

	// Fields

	private CgCityId id;

	// Constructors

	/** default constructor */
	public CgCity() {
	}

	/** full constructor */
	public CgCity(CgCityId id) {
		this.id = id;
	}

	// Property accessors

	public CgCityId getId() {
		return this.id;
	}

	public void setId(CgCityId id) {
		this.id = id;
	}

}