package com.utoo.chunguanyouli.dbentity;

/**
 * CgReply entity. @author MyEclipse Persistence Tools
 */

public class CgReply implements java.io.Serializable {

	// Fields

	private CgReplyId id;

	// Constructors

	/** default constructor */
	public CgReply() {
	}

	/** full constructor */
	public CgReply(CgReplyId id) {
		this.id = id;
	}

	// Property accessors

	public CgReplyId getId() {
		return this.id;
	}

	public void setId(CgReplyId id) {
		this.id = id;
	}

}