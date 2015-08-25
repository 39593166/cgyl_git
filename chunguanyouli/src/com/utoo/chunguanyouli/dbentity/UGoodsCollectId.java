package com.utoo.chunguanyouli.dbentity;

import java.sql.Timestamp;

/**
 * UGoodsCollectId entity. @author MyEclipse Persistence Tools
 */

public class UGoodsCollectId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer goodsid;
	private Integer userid;
	private Timestamp dateset;

	// Constructors

	/** default constructor */
	public UGoodsCollectId() {
	}

	/** full constructor */
	public UGoodsCollectId(Integer id, Integer goodsid, Integer userid,
			Timestamp dateset) {
		this.id = id;
		this.goodsid = goodsid;
		this.userid = userid;
		this.dateset = dateset;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
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
		if (!(other instanceof UGoodsCollectId))
			return false;
		UGoodsCollectId castOther = (UGoodsCollectId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getGoodsid() == castOther.getGoodsid()) || (this
						.getGoodsid() != null && castOther.getGoodsid() != null && this
						.getGoodsid().equals(castOther.getGoodsid())))
				&& ((this.getUserid() == castOther.getUserid()) || (this
						.getUserid() != null && castOther.getUserid() != null && this
						.getUserid().equals(castOther.getUserid())))
				&& ((this.getDateset() == castOther.getDateset()) || (this
						.getDateset() != null && castOther.getDateset() != null && this
						.getDateset().equals(castOther.getDateset())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getGoodsid() == null ? 0 : this.getGoodsid().hashCode());
		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result
				+ (getDateset() == null ? 0 : this.getDateset().hashCode());
		return result;
	}

}