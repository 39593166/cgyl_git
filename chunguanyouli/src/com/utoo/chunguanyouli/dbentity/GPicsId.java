package com.utoo.chunguanyouli.dbentity;

import java.sql.Timestamp;

/**
 * GPicsId entity. @author MyEclipse Persistence Tools
 */

public class GPicsId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String url;
	private Integer goodsid;
	private Integer orderid;
	private Timestamp datesend;
	private Timestamp dateshow;
	private String title;
	private Integer typeid;

	// Constructors

	/** default constructor */
	public GPicsId() {
	}

	/** full constructor */
	public GPicsId(Integer id, String url, Integer goodsid, Integer orderid,
			Timestamp datesend, Timestamp dateshow, String title, Integer typeid) {
		this.id = id;
		this.url = url;
		this.goodsid = goodsid;
		this.orderid = orderid;
		this.datesend = datesend;
		this.dateshow = dateshow;
		this.title = title;
		this.typeid = typeid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	public Integer getOrderid() {
		return this.orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public Timestamp getDatesend() {
		return this.datesend;
	}

	public void setDatesend(Timestamp datesend) {
		this.datesend = datesend;
	}

	public Timestamp getDateshow() {
		return this.dateshow;
	}

	public void setDateshow(Timestamp dateshow) {
		this.dateshow = dateshow;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTypeid() {
		return this.typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GPicsId))
			return false;
		GPicsId castOther = (GPicsId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getUrl() == castOther.getUrl()) || (this.getUrl() != null
						&& castOther.getUrl() != null && this.getUrl().equals(
						castOther.getUrl())))
				&& ((this.getGoodsid() == castOther.getGoodsid()) || (this
						.getGoodsid() != null && castOther.getGoodsid() != null && this
						.getGoodsid().equals(castOther.getGoodsid())))
				&& ((this.getOrderid() == castOther.getOrderid()) || (this
						.getOrderid() != null && castOther.getOrderid() != null && this
						.getOrderid().equals(castOther.getOrderid())))
				&& ((this.getDatesend() == castOther.getDatesend()) || (this
						.getDatesend() != null
						&& castOther.getDatesend() != null && this
						.getDatesend().equals(castOther.getDatesend())))
				&& ((this.getDateshow() == castOther.getDateshow()) || (this
						.getDateshow() != null
						&& castOther.getDateshow() != null && this
						.getDateshow().equals(castOther.getDateshow())))
				&& ((this.getTitle() == castOther.getTitle()) || (this
						.getTitle() != null && castOther.getTitle() != null && this
						.getTitle().equals(castOther.getTitle())))
				&& ((this.getTypeid() == castOther.getTypeid()) || (this
						.getTypeid() != null && castOther.getTypeid() != null && this
						.getTypeid().equals(castOther.getTypeid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getUrl() == null ? 0 : this.getUrl().hashCode());
		result = 37 * result
				+ (getGoodsid() == null ? 0 : this.getGoodsid().hashCode());
		result = 37 * result
				+ (getOrderid() == null ? 0 : this.getOrderid().hashCode());
		result = 37 * result
				+ (getDatesend() == null ? 0 : this.getDatesend().hashCode());
		result = 37 * result
				+ (getDateshow() == null ? 0 : this.getDateshow().hashCode());
		result = 37 * result
				+ (getTitle() == null ? 0 : this.getTitle().hashCode());
		result = 37 * result
				+ (getTypeid() == null ? 0 : this.getTypeid().hashCode());
		return result;
	}

}