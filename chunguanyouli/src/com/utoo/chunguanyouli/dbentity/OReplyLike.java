package com.utoo.chunguanyouli.dbentity;

/**
 * OReplyLike entity. @author MyEclipse Persistence Tools
 */

public class OReplyLike implements java.io.Serializable {

	// Fields

	private OReplyLikeId id;

	// Constructors

	/** default constructor */
	public OReplyLike() {
	}

	/** full constructor */
	public OReplyLike(OReplyLikeId id) {
		this.id = id;
	}

	// Property accessors

	public OReplyLikeId getId() {
		return this.id;
	}

	public void setId(OReplyLikeId id) {
		this.id = id;
	}

}