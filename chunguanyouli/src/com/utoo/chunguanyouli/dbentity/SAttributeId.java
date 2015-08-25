package com.utoo.chunguanyouli.dbentity;

/**
 * SAttributeId entity. @author MyEclipse Persistence Tools
 */

public class SAttributeId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer pid;

	// Constructors

	/** default constructor */
	public SAttributeId() {
	}

	/** full constructor */
	public SAttributeId(Integer id, String name, Integer pid) {
		this.id = id;
		this.name = name;
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
		if (!(other instanceof SAttributeId))
			return false;
		SAttributeId castOther = (SAttributeId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
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
				+ (getPid() == null ? 0 : this.getPid().hashCode());
		return result;
	}

}