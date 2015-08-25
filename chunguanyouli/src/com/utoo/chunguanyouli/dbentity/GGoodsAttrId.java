package com.utoo.chunguanyouli.dbentity;

/**
 * GGoodsAttrId entity. @author MyEclipse Persistence Tools
 */

public class GGoodsAttrId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer goodsid;
	private Double pricemarkat;
	private Double pricemember;
	private Integer numhave;
	private String attrids;

	// Constructors

	/** default constructor */
	public GGoodsAttrId() {
	}

	/** full constructor */
	public GGoodsAttrId(Integer id, Integer goodsid, Double pricemarkat,
			Double pricemember, Integer numhave, String attrids) {
		this.id = id;
		this.goodsid = goodsid;
		this.pricemarkat = pricemarkat;
		this.pricemember = pricemember;
		this.numhave = numhave;
		this.attrids = attrids;
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

	public Double getPricemarkat() {
		return this.pricemarkat;
	}

	public void setPricemarkat(Double pricemarkat) {
		this.pricemarkat = pricemarkat;
	}

	public Double getPricemember() {
		return this.pricemember;
	}

	public void setPricemember(Double pricemember) {
		this.pricemember = pricemember;
	}

	public Integer getNumhave() {
		return this.numhave;
	}

	public void setNumhave(Integer numhave) {
		this.numhave = numhave;
	}

	public String getAttrids() {
		return this.attrids;
	}

	public void setAttrids(String attrids) {
		this.attrids = attrids;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GGoodsAttrId))
			return false;
		GGoodsAttrId castOther = (GGoodsAttrId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getGoodsid() == castOther.getGoodsid()) || (this
						.getGoodsid() != null && castOther.getGoodsid() != null && this
						.getGoodsid().equals(castOther.getGoodsid())))
				&& ((this.getPricemarkat() == castOther.getPricemarkat()) || (this
						.getPricemarkat() != null
						&& castOther.getPricemarkat() != null && this
						.getPricemarkat().equals(castOther.getPricemarkat())))
				&& ((this.getPricemember() == castOther.getPricemember()) || (this
						.getPricemember() != null
						&& castOther.getPricemember() != null && this
						.getPricemember().equals(castOther.getPricemember())))
				&& ((this.getNumhave() == castOther.getNumhave()) || (this
						.getNumhave() != null && castOther.getNumhave() != null && this
						.getNumhave().equals(castOther.getNumhave())))
				&& ((this.getAttrids() == castOther.getAttrids()) || (this
						.getAttrids() != null && castOther.getAttrids() != null && this
						.getAttrids().equals(castOther.getAttrids())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getGoodsid() == null ? 0 : this.getGoodsid().hashCode());
		result = 37
				* result
				+ (getPricemarkat() == null ? 0 : this.getPricemarkat()
						.hashCode());
		result = 37
				* result
				+ (getPricemember() == null ? 0 : this.getPricemember()
						.hashCode());
		result = 37 * result
				+ (getNumhave() == null ? 0 : this.getNumhave().hashCode());
		result = 37 * result
				+ (getAttrids() == null ? 0 : this.getAttrids().hashCode());
		return result;
	}

}