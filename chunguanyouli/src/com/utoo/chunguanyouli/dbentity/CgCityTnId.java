package com.utoo.chunguanyouli.dbentity;


/**
 * CgCityTnId entity. @author MyEclipse Persistence Tools
 */

public class CgCityTnId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private Integer tid;
	private String datesend;
	private Integer cid;

	// Constructors

	/** default constructor */
	public CgCityTnId() {
	}

	/** minimal constructor */
	public CgCityTnId(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public CgCityTnId(Integer id, Integer uid, Integer tid, String datesend,
			Integer cid) {
		this.id = id;
		this.uid = uid;
		this.tid = tid;
		this.datesend = datesend;
		this.cid = cid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getTid() {
		return this.tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getDatesend() {
		return this.datesend;
	}

	public void setDatesend(String datesend) {
		this.datesend = datesend;
	}

	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CgCityTnId))
			return false;
		CgCityTnId castOther = (CgCityTnId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getUid() == castOther.getUid()) || (this.getUid() != null
						&& castOther.getUid() != null && this.getUid().equals(
						castOther.getUid())))
				&& ((this.getTid() == castOther.getTid()) || (this.getTid() != null
						&& castOther.getTid() != null && this.getTid().equals(
						castOther.getTid())))
				&& ((this.getDatesend() == castOther.getDatesend()) || (this
						.getDatesend() != null
						&& castOther.getDatesend() != null && this
						.getDatesend().equals(castOther.getDatesend())))
				&& ((this.getCid() == castOther.getCid()) || (this.getCid() != null
						&& castOther.getCid() != null && this.getCid().equals(
						castOther.getCid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getUid() == null ? 0 : this.getUid().hashCode());
		result = 37 * result
				+ (getTid() == null ? 0 : this.getTid().hashCode());
		result = 37 * result
				+ (getDatesend() == null ? 0 : this.getDatesend().hashCode());
		result = 37 * result
				+ (getCid() == null ? 0 : this.getCid().hashCode());
		return result;
	}

}