package com.utoo.chunguanyouli.dbentity;

/**
 * GGoodsSetAttrId entity. @author MyEclipse Persistence Tools
 */

public class GGoodsSetAttrId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer cid;
	private String attrstr;
	private Integer goodsid;

	// Constructors

	/** default constructor */
	public GGoodsSetAttrId() {
	}

	/** full constructor */
	public GGoodsSetAttrId(Integer id, Integer cid, String attrstr,
			Integer goodsid) {
		this.id = id;
		this.cid = cid;
		this.attrstr = attrstr;
		this.goodsid = goodsid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getAttrstr() {
		return this.attrstr;
	}

	public void setAttrstr(String attrstr) {
		this.attrstr = attrstr;
	}

	public Integer getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GGoodsSetAttrId))
			return false;
		GGoodsSetAttrId castOther = (GGoodsSetAttrId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getCid() == castOther.getCid()) || (this.getCid() != null
						&& castOther.getCid() != null && this.getCid().equals(
						castOther.getCid())))
				&& ((this.getAttrstr() == castOther.getAttrstr()) || (this
						.getAttrstr() != null && castOther.getAttrstr() != null && this
						.getAttrstr().equals(castOther.getAttrstr())))
				&& ((this.getGoodsid() == castOther.getGoodsid()) || (this
						.getGoodsid() != null && castOther.getGoodsid() != null && this
						.getGoodsid().equals(castOther.getGoodsid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getCid() == null ? 0 : this.getCid().hashCode());
		result = 37 * result
				+ (getAttrstr() == null ? 0 : this.getAttrstr().hashCode());
		result = 37 * result
				+ (getGoodsid() == null ? 0 : this.getGoodsid().hashCode());
		return result;
	}

}