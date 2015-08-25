package com.utoo.chunguanyouli.dbentity;

import java.sql.Timestamp;

/**
 * UDeliveryAddressId entity. @author MyEclipse Persistence Tools
 */

public class UDeliveryAddressId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private String area;
	private String address;
	private String zip;
	private String receiver;
	private String phone;
	private String mobile;
	private Integer isusual;
	private Timestamp dateset;

	// Constructors

	/** default constructor */
	public UDeliveryAddressId() {
	}

	/** full constructor */
	public UDeliveryAddressId(Integer id, Integer userid, String area,
			String address, String zip, String receiver, String phone,
			String mobile, Integer isusual, Timestamp dateset) {
		this.id = id;
		this.userid = userid;
		this.area = area;
		this.address = address;
		this.zip = zip;
		this.receiver = receiver;
		this.phone = phone;
		this.mobile = mobile;
		this.isusual = isusual;
		this.dateset = dateset;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getIsusual() {
		return this.isusual;
	}

	public void setIsusual(Integer isusual) {
		this.isusual = isusual;
	}

	public Timestamp getDateset() {
		return this.dateset;
	}

	public void setDateset(Timestamp dateset) {
		this.dateset = dateset;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UDeliveryAddressId))
			return false;
		UDeliveryAddressId castOther = (UDeliveryAddressId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getUserid() == castOther.getUserid()) || (this
						.getUserid() != null && castOther.getUserid() != null && this
						.getUserid().equals(castOther.getUserid())))
				&& ((this.getArea() == castOther.getArea()) || (this.getArea() != null
						&& castOther.getArea() != null && this.getArea()
						.equals(castOther.getArea())))
				&& ((this.getAddress() == castOther.getAddress()) || (this
						.getAddress() != null && castOther.getAddress() != null && this
						.getAddress().equals(castOther.getAddress())))
				&& ((this.getZip() == castOther.getZip()) || (this.getZip() != null
						&& castOther.getZip() != null && this.getZip().equals(
						castOther.getZip())))
				&& ((this.getReceiver() == castOther.getReceiver()) || (this
						.getReceiver() != null
						&& castOther.getReceiver() != null && this
						.getReceiver().equals(castOther.getReceiver())))
				&& ((this.getPhone() == castOther.getPhone()) || (this
						.getPhone() != null && castOther.getPhone() != null && this
						.getPhone().equals(castOther.getPhone())))
				&& ((this.getMobile() == castOther.getMobile()) || (this
						.getMobile() != null && castOther.getMobile() != null && this
						.getMobile().equals(castOther.getMobile())))
				&& ((this.getIsusual() == castOther.getIsusual()) || (this
						.getIsusual() != null && castOther.getIsusual() != null && this
						.getIsusual().equals(castOther.getIsusual())))
				&& ((this.getDateset() == castOther.getDateset()) || (this
						.getDateset() != null && castOther.getDateset() != null && this
						.getDateset().equals(castOther.getDateset())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result
				+ (getArea() == null ? 0 : this.getArea().hashCode());
		result = 37 * result
				+ (getAddress() == null ? 0 : this.getAddress().hashCode());
		result = 37 * result
				+ (getZip() == null ? 0 : this.getZip().hashCode());
		result = 37 * result
				+ (getReceiver() == null ? 0 : this.getReceiver().hashCode());
		result = 37 * result
				+ (getPhone() == null ? 0 : this.getPhone().hashCode());
		result = 37 * result
				+ (getMobile() == null ? 0 : this.getMobile().hashCode());
		result = 37 * result
				+ (getIsusual() == null ? 0 : this.getIsusual().hashCode());
		result = 37 * result
				+ (getDateset() == null ? 0 : this.getDateset().hashCode());
		return result;
	}

}