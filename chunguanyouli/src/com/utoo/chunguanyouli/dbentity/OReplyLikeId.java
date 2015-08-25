package com.utoo.chunguanyouli.dbentity;

/**
 * OReplyLikeId entity. @author MyEclipse Persistence Tools
 */

public class OReplyLikeId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer useid;
	private Integer rid;
	private Boolean islike;

	// Constructors

	/** default constructor */
	public OReplyLikeId() {
	}

	/** full constructor */
	public OReplyLikeId(Integer id, Integer useid, Integer rid, Boolean islike) {
		this.id = id;
		this.useid = useid;
		this.rid = rid;
		this.islike = islike;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUseid() {
		return this.useid;
	}

	public void setUseid(Integer useid) {
		this.useid = useid;
	}

	public Integer getRid() {
		return this.rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Boolean getIslike() {
		return this.islike;
	}

	public void setIslike(Boolean islike) {
		this.islike = islike;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OReplyLikeId))
			return false;
		OReplyLikeId castOther = (OReplyLikeId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getUseid() == castOther.getUseid()) || (this
						.getUseid() != null && castOther.getUseid() != null && this
						.getUseid().equals(castOther.getUseid())))
				&& ((this.getRid() == castOther.getRid()) || (this.getRid() != null
						&& castOther.getRid() != null && this.getRid().equals(
						castOther.getRid())))
				&& ((this.getIslike() == castOther.getIslike()) || (this
						.getIslike() != null && castOther.getIslike() != null && this
						.getIslike().equals(castOther.getIslike())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getUseid() == null ? 0 : this.getUseid().hashCode());
		result = 37 * result
				+ (getRid() == null ? 0 : this.getRid().hashCode());
		result = 37 * result
				+ (getIslike() == null ? 0 : this.getIslike().hashCode());
		return result;
	}

}