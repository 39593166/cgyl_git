package com.utoo.chunguanyouli.dbentity;

/**
 * ViewReList entity. @author MyEclipse Persistence Tools
 */

public class ViewReList implements java.io.Serializable {

	// Fields

	private ViewReListId id;

	// Constructors

	/** default constructor */
	public ViewReList() {
	}

	/** full constructor */
	public ViewReList(ViewReListId id) {
		this.id = id;
	}

	// Property accessors

	public ViewReListId getId() {
		return this.id;
	}

	public void setId(ViewReListId id) {
		this.id = id;
	}

}