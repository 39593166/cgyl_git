package com.utoo.chunguanyouli.dbentity;

/**
 * UDeliveryAddress entity. @author MyEclipse Persistence Tools
 */

public class UDeliveryAddress implements java.io.Serializable {

	// Fields

	private UDeliveryAddressId id;

	// Constructors

	/** default constructor */
	public UDeliveryAddress() {
	}

	/** full constructor */
	public UDeliveryAddress(UDeliveryAddressId id) {
		this.id = id;
	}

	// Property accessors

	public UDeliveryAddressId getId() {
		return this.id;
	}

	public void setId(UDeliveryAddressId id) {
		this.id = id;
	}

}