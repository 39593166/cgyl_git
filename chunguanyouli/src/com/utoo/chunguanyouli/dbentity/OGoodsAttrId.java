package com.utoo.chunguanyouli.dbentity;

/**
 * OGoodsAttrId entity. @author MyEclipse Persistence Tools
 */

public class OGoodsAttrId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String attrname;
	private Integer attrid;
	private Integer ogid;

	// Constructors

	/** default constructor */
	public OGoodsAttrId() {
	}

	/** full constructor */
	public OGoodsAttrId(Integer id, String attrname, Integer attrid,
			Integer ogid) {
		this.id = id;
		this.attrname = attrname;
		this.attrid = attrid;
		this.ogid = ogid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAttrname() {
		return this.attrname;
	}

	public void setAttrname(String attrname) {
		this.attrname = attrname;
	}

	public Integer getAttrid() {
		return this.attrid;
	}

	public void setAttrid(Integer attrid) {
		this.attrid = attrid;
	}

	public Integer getOgid() {
		return this.ogid;
	}

	public void setOgid(Integer ogid) {
		this.ogid = ogid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OGoodsAttrId))
			return false;
		OGoodsAttrId castOther = (OGoodsAttrId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getAttrname() == castOther.getAttrname()) || (this
						.getAttrname() != null
						&& castOther.getAttrname() != null && this
						.getAttrname().equals(castOther.getAttrname())))
				&& ((this.getAttrid() == castOther.getAttrid()) || (this
						.getAttrid() != null && castOther.getAttrid() != null && this
						.getAttrid().equals(castOther.getAttrid())))
				&& ((this.getOgid() == castOther.getOgid()) || (this.getOgid() != null
						&& castOther.getOgid() != null && this.getOgid()
						.equals(castOther.getOgid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getAttrname() == null ? 0 : this.getAttrname().hashCode());
		result = 37 * result
				+ (getAttrid() == null ? 0 : this.getAttrid().hashCode());
		result = 37 * result
				+ (getOgid() == null ? 0 : this.getOgid().hashCode());
		return result;
	}

}