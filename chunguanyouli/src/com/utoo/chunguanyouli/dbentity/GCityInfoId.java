package com.utoo.chunguanyouli.dbentity;

/**
 * GCityInfoId entity. @author MyEclipse Persistence Tools
 */

public class GCityInfoId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer gid;
	private String code;
	private Integer pid;

	// Constructors

	/** default constructor */
	public GCityInfoId() {
	}

	/** full constructor */
	public GCityInfoId(Integer id, String name, Integer gid, String code,
			Integer pid) {
		this.id = id;
		this.name = name;
		this.gid = gid;
		this.code = code;
		this.pid = pid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGid() {
		return this.gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GCityInfoId))
			return false;
		GCityInfoId castOther = (GCityInfoId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getGid() == castOther.getGid()) || (this.getGid() != null
						&& castOther.getGid() != null && this.getGid().equals(
						castOther.getGid())))
				&& ((this.getCode() == castOther.getCode()) || (this.getCode() != null
						&& castOther.getCode() != null && this.getCode()
						.equals(castOther.getCode())))
				&& ((this.getPid() == castOther.getPid()) || (this.getPid() != null
						&& castOther.getPid() != null && this.getPid().equals(
						castOther.getPid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getGid() == null ? 0 : this.getGid().hashCode());
		result = 37 * result
				+ (getCode() == null ? 0 : this.getCode().hashCode());
		result = 37 * result
				+ (getPid() == null ? 0 : this.getPid().hashCode());
		return result;
	}

}