package com.utoo.chunguanyouli.dbentity;

import java.sql.Timestamp;

/**
 * OFormId entity. @author MyEclipse Persistence Tools
 */

public class OFormId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String numstr;
	private Timestamp dateset;
	private Integer userid;
	private Double goodsprice;
	private Double postage;
	private Integer paytype;
	private String bankstr;
	private String rearea;
	private String readdress;
	private String rezip;
	private String receiver;
	private String rephone;
	private String remobile;
	private Integer deliveryid;
	private String ofrom;

	// Constructors

	/** default constructor */
	public OFormId() {
	}

	/** full constructor */
	public OFormId(Integer id, String numstr, Timestamp dateset,
			Integer userid, Double goodsprice, Double postage, Integer paytype,
			String bankstr, String rearea, String readdress, String rezip,
			String receiver, String rephone, String remobile,
			Integer deliveryid, String ofrom) {
		this.id = id;
		this.numstr = numstr;
		this.dateset = dateset;
		this.userid = userid;
		this.goodsprice = goodsprice;
		this.postage = postage;
		this.paytype = paytype;
		this.bankstr = bankstr;
		this.rearea = rearea;
		this.readdress = readdress;
		this.rezip = rezip;
		this.receiver = receiver;
		this.rephone = rephone;
		this.remobile = remobile;
		this.deliveryid = deliveryid;
		this.ofrom = ofrom;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumstr() {
		return this.numstr;
	}

	public void setNumstr(String numstr) {
		this.numstr = numstr;
	}

	public Timestamp getDateset() {
		return this.dateset;
	}

	public void setDateset(Timestamp dateset) {
		this.dateset = dateset;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Double getGoodsprice() {
		return this.goodsprice;
	}

	public void setGoodsprice(Double goodsprice) {
		this.goodsprice = goodsprice;
	}

	public Double getPostage() {
		return this.postage;
	}

	public void setPostage(Double postage) {
		this.postage = postage;
	}

	public Integer getPaytype() {
		return this.paytype;
	}

	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}

	public String getBankstr() {
		return this.bankstr;
	}

	public void setBankstr(String bankstr) {
		this.bankstr = bankstr;
	}

	public String getRearea() {
		return this.rearea;
	}

	public void setRearea(String rearea) {
		this.rearea = rearea;
	}

	public String getReaddress() {
		return this.readdress;
	}

	public void setReaddress(String readdress) {
		this.readdress = readdress;
	}

	public String getRezip() {
		return this.rezip;
	}

	public void setRezip(String rezip) {
		this.rezip = rezip;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getRephone() {
		return this.rephone;
	}

	public void setRephone(String rephone) {
		this.rephone = rephone;
	}

	public String getRemobile() {
		return this.remobile;
	}

	public void setRemobile(String remobile) {
		this.remobile = remobile;
	}

	public Integer getDeliveryid() {
		return this.deliveryid;
	}

	public void setDeliveryid(Integer deliveryid) {
		this.deliveryid = deliveryid;
	}

	public String getOfrom() {
		return this.ofrom;
	}

	public void setOfrom(String ofrom) {
		this.ofrom = ofrom;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OFormId))
			return false;
		OFormId castOther = (OFormId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getNumstr() == castOther.getNumstr()) || (this
						.getNumstr() != null && castOther.getNumstr() != null && this
						.getNumstr().equals(castOther.getNumstr())))
				&& ((this.getDateset() == castOther.getDateset()) || (this
						.getDateset() != null && castOther.getDateset() != null && this
						.getDateset().equals(castOther.getDateset())))
				&& ((this.getUserid() == castOther.getUserid()) || (this
						.getUserid() != null && castOther.getUserid() != null && this
						.getUserid().equals(castOther.getUserid())))
				&& ((this.getGoodsprice() == castOther.getGoodsprice()) || (this
						.getGoodsprice() != null
						&& castOther.getGoodsprice() != null && this
						.getGoodsprice().equals(castOther.getGoodsprice())))
				&& ((this.getPostage() == castOther.getPostage()) || (this
						.getPostage() != null && castOther.getPostage() != null && this
						.getPostage().equals(castOther.getPostage())))
				&& ((this.getPaytype() == castOther.getPaytype()) || (this
						.getPaytype() != null && castOther.getPaytype() != null && this
						.getPaytype().equals(castOther.getPaytype())))
				&& ((this.getBankstr() == castOther.getBankstr()) || (this
						.getBankstr() != null && castOther.getBankstr() != null && this
						.getBankstr().equals(castOther.getBankstr())))
				&& ((this.getRearea() == castOther.getRearea()) || (this
						.getRearea() != null && castOther.getRearea() != null && this
						.getRearea().equals(castOther.getRearea())))
				&& ((this.getReaddress() == castOther.getReaddress()) || (this
						.getReaddress() != null
						&& castOther.getReaddress() != null && this
						.getReaddress().equals(castOther.getReaddress())))
				&& ((this.getRezip() == castOther.getRezip()) || (this
						.getRezip() != null && castOther.getRezip() != null && this
						.getRezip().equals(castOther.getRezip())))
				&& ((this.getReceiver() == castOther.getReceiver()) || (this
						.getReceiver() != null
						&& castOther.getReceiver() != null && this
						.getReceiver().equals(castOther.getReceiver())))
				&& ((this.getRephone() == castOther.getRephone()) || (this
						.getRephone() != null && castOther.getRephone() != null && this
						.getRephone().equals(castOther.getRephone())))
				&& ((this.getRemobile() == castOther.getRemobile()) || (this
						.getRemobile() != null
						&& castOther.getRemobile() != null && this
						.getRemobile().equals(castOther.getRemobile())))
				&& ((this.getDeliveryid() == castOther.getDeliveryid()) || (this
						.getDeliveryid() != null
						&& castOther.getDeliveryid() != null && this
						.getDeliveryid().equals(castOther.getDeliveryid())))
				&& ((this.getOfrom() == castOther.getOfrom()) || (this
						.getOfrom() != null && castOther.getOfrom() != null && this
						.getOfrom().equals(castOther.getOfrom())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getNumstr() == null ? 0 : this.getNumstr().hashCode());
		result = 37 * result
				+ (getDateset() == null ? 0 : this.getDateset().hashCode());
		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37
				* result
				+ (getGoodsprice() == null ? 0 : this.getGoodsprice()
						.hashCode());
		result = 37 * result
				+ (getPostage() == null ? 0 : this.getPostage().hashCode());
		result = 37 * result
				+ (getPaytype() == null ? 0 : this.getPaytype().hashCode());
		result = 37 * result
				+ (getBankstr() == null ? 0 : this.getBankstr().hashCode());
		result = 37 * result
				+ (getRearea() == null ? 0 : this.getRearea().hashCode());
		result = 37 * result
				+ (getReaddress() == null ? 0 : this.getReaddress().hashCode());
		result = 37 * result
				+ (getRezip() == null ? 0 : this.getRezip().hashCode());
		result = 37 * result
				+ (getReceiver() == null ? 0 : this.getReceiver().hashCode());
		result = 37 * result
				+ (getRephone() == null ? 0 : this.getRephone().hashCode());
		result = 37 * result
				+ (getRemobile() == null ? 0 : this.getRemobile().hashCode());
		result = 37
				* result
				+ (getDeliveryid() == null ? 0 : this.getDeliveryid()
						.hashCode());
		result = 37 * result
				+ (getOfrom() == null ? 0 : this.getOfrom().hashCode());
		return result;
	}

}