package com.utoo.chunguanyouli.dbentity;

/**
 * GCategoryAttrId entity. @author MyEclipse Persistence Tools
 */

public class GCategoryAttrId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer cid;
	private String name;
	private Boolean isnum;
	private Boolean isprice;

	// Constructors

	/** default constructor */
	public GCategoryAttrId() {
	}

	/** full constructor */
	public GCategoryAttrId(Integer id, Integer cid, String name, Boolean isnum,
			Boolean isprice) {
		this.id = id;
		this.cid = cid;
		this.name = name;
		this.isnum = isnum;
		this.isprice = isprice;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsnum() {
		return this.isnum;
	}

	public void setIsnum(Boolean isnum) {
		this.isnum = isnum;
	}

	public Boolean getIsprice() {
		return this.isprice;
	}

	public void setIsprice(Boolean isprice) {
		this.isprice = isprice;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GCategoryAttrId))
			return false;
		GCategoryAttrId castOther = (GCategoryAttrId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getCid() == castOther.getCid()) || (this.getCid() != null
						&& castOther.getCid() != null && this.getCid().equals(
						castOther.getCid())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getIsnum() == castOther.getIsnum()) || (this
						.getIsnum() != null && castOther.getIsnum() != null && this
						.getIsnum().equals(castOther.getIsnum())))
				&& ((this.getIsprice() == castOther.getIsprice()) || (this
						.getIsprice() != null && castOther.getIsprice() != null && this
						.getIsprice().equals(castOther.getIsprice())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getCid() == null ? 0 : this.getCid().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getIsnum() == null ? 0 : this.getIsnum().hashCode());
		result = 37 * result
				+ (getIsprice() == null ? 0 : this.getIsprice().hashCode());
		return result;
	}

}