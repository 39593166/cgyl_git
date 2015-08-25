package com.utoo.chunguanyouli.dbentity;

import java.sql.Timestamp;

/**
 * CgConcernId entity. @author MyEclipse Persistence Tools
 */

public class CgConcernId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private Timestamp datasend;
	private Integer bid;
	private Integer tid;

	// Constructors

	/** default constructor */
	public CgConcernId() {
	}

	/** minimal constructor */
	public CgConcernId(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public CgConcernId(Integer id, Integer uid, Timestamp datasend,
			Integer bid, Integer tid) {
		this.id = id;
		this.uid = uid;
		this.datasend = datasend;
		this.bid = bid;
		this.tid = tid;
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

	public Timestamp getDatasend() {
		return this.datasend;
	}

	public void setDatasend(Timestamp datasend) {
		this.datasend = datasend;
	}

	public Integer getBid() {
		return this.bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public Integer getTid() {
		return this.tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CgConcernId))
			return false;
		CgConcernId castOther = (CgConcernId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getUid() == castOther.getUid()) || (this.getUid() != null
						&& castOther.getUid() != null && this.getUid().equals(
						castOther.getUid())))
				&& ((this.getDatasend() == castOther.getDatasend()) || (this
						.getDatasend() != null
						&& castOther.getDatasend() != null && this
						.getDatasend().equals(castOther.getDatasend())))
				&& ((this.getBid() == castOther.getBid()) || (this.getBid() != null
						&& castOther.getBid() != null && this.getBid().equals(
						castOther.getBid())))
				&& ((this.getTid() == castOther.getTid()) || (this.getTid() != null
						&& castOther.getTid() != null && this.getTid().equals(
						castOther.getTid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getUid() == null ? 0 : this.getUid().hashCode());
		result = 37 * result
				+ (getDatasend() == null ? 0 : this.getDatasend().hashCode());
		result = 37 * result
				+ (getBid() == null ? 0 : this.getBid().hashCode());
		result = 37 * result
				+ (getTid() == null ? 0 : this.getTid().hashCode());
		return result;
	}

}