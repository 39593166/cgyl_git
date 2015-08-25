package com.utoo.chunguanyouli.dbentity;

/**
 * CgCityTn entity. @author MyEclipse Persistence Tools
 */

public class CgCityTn implements java.io.Serializable {

	// Fields

	private CgCityTnId id;

	// Constructors

	/** default constructor */
	public CgCityTn() {
	}

	/** full constructor */
	public CgCityTn(CgCityTnId id) {
		this.id = id;
	}

	// Property accessors

	public CgCityTnId getId() {
		return this.id;
	}

	public void setId(CgCityTnId id) {
		this.id = id;
	}

}