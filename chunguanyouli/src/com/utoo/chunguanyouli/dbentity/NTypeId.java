package com.utoo.chunguanyouli.dbentity;

/**
 * NTypeId entity. @author MyEclipse Persistence Tools
 */

public class NTypeId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String typename;

	// Constructors

	/** default constructor */
	public NTypeId() {
	}

	/** minimal constructor */
	public NTypeId(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public NTypeId(Integer id, String typename) {
		this.id = id;
		this.typename = typename;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypename() {
		return this.typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof NTypeId))
			return false;
		NTypeId castOther = (NTypeId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getTypename() == castOther.getTypename()) || (this
						.getTypename() != null
						&& castOther.getTypename() != null && this
						.getTypename().equals(castOther.getTypename())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getTypename() == null ? 0 : this.getTypename().hashCode());
		return result;
	}

}