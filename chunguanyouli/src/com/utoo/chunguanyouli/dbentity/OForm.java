package com.utoo.chunguanyouli.dbentity;

/**
 * OForm entity. @author MyEclipse Persistence Tools
 */

public class OForm implements java.io.Serializable {

	// Fields

	private OFormId id;

	// Constructors

	/** default constructor */
	public OForm() {
	}

	/** full constructor */
	public OForm(OFormId id) {
		this.id = id;
	}

	// Property accessors

	public OFormId getId() {
		return this.id;
	}

	public void setId(OFormId id) {
		this.id = id;
	}

}