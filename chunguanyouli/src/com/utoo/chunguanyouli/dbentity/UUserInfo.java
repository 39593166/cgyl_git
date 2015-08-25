package com.utoo.chunguanyouli.dbentity;

/**
 * UUserInfo entity. @author MyEclipse Persistence Tools
 */

public class UUserInfo implements java.io.Serializable {

	// Fields

	private UUserInfoId id;

	// Constructors

	/** default constructor */
	public UUserInfo() {
	}

	/** full constructor */
	public UUserInfo(UUserInfoId id) {
		this.id = id;
	}

	// Property accessors

	public UUserInfoId getId() {
		return this.id;
	}

	public void setId(UUserInfoId id) {
		this.id = id;
	}

}