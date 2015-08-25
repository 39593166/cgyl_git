package com.utoo.chunguanyouli.dbentity;

/**
 * NNewsInfo entity. @author MyEclipse Persistence Tools
 */

public class NNewsInfo implements java.io.Serializable {

	// Fields

	private NNewsInfoId id;

	// Constructors

	/** default constructor */
	public NNewsInfo() {
	}

	/** full constructor */
	public NNewsInfo(NNewsInfoId id) {
		this.id = id;
	}

	// Property accessors

	public NNewsInfoId getId() {
		return this.id;
	}

	public void setId(NNewsInfoId id) {
		this.id = id;
	}

}