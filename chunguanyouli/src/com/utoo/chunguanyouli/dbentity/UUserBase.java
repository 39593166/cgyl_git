package com.utoo.chunguanyouli.dbentity;

/**
 * UUserBase entity. @author MyEclipse Persistence Tools
 */

public class UUserBase implements java.io.Serializable {

	// Fields

	private UUserBaseId id;

	// Constructors

	/** default constructor */
	public UUserBase() {
	}

	/** full constructor */
	public UUserBase(UUserBaseId id) {
		this.id = id;
	}

	// Property accessors

	public UUserBaseId getId() {
		return this.id;
	}

	public void setId(UUserBaseId id) {
		this.id = id;
	}

}