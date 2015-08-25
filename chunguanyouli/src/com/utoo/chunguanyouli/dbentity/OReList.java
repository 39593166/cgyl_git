package com.utoo.chunguanyouli.dbentity;

/**
 * OReList entity. @author MyEclipse Persistence Tools
 */

public class OReList implements java.io.Serializable {

	// Fields

	private OReListId id;

	// Constructors

	/** default constructor */
	public OReList() {
	}

	/** full constructor */
	public OReList(OReListId id) {
		this.id = id;
	}

	// Property accessors

	public OReListId getId() {
		return this.id;
	}

	public void setId(OReListId id) {
		this.id = id;
	}

}