package com.utoo.chunguanyouli.dbentity;

/**
 * CgStoreInfo entity. @author MyEclipse Persistence Tools
 */

public class CgStoreInfo implements java.io.Serializable {

	// Fields

	private CgStoreInfoId id;

	// Constructors

	/** default constructor */
	public CgStoreInfo() {
	}

	/** full constructor */
	public CgStoreInfo(CgStoreInfoId id) {
		this.id = id;
	}

	// Property accessors

	public CgStoreInfoId getId() {
		return this.id;
	}

	public void setId(CgStoreInfoId id) {
		this.id = id;
	}

}