package com.utoo.chunguanyouli.dbentity;

/**
 * CgUserId entity. @author MyEclipse Persistence Tools
 */

public class CgUserId implements java.io.Serializable {

	// Fields

	private Integer uid;
	private String job;

	// Constructors

	/** default constructor */
	public CgUserId() {
	}

	/** full constructor */
	public CgUserId(Integer uid, String job) {
		this.uid = uid;
		this.job = job;
	}

	// Property accessors

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CgUserId))
			return false;
		CgUserId castOther = (CgUserId) other;

		return ((this.getUid() == castOther.getUid()) || (this.getUid() != null
				&& castOther.getUid() != null && this.getUid().equals(
				castOther.getUid())))
				&& ((this.getJob() == castOther.getJob()) || (this.getJob() != null
						&& castOther.getJob() != null && this.getJob().equals(
						castOther.getJob())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUid() == null ? 0 : this.getUid().hashCode());
		result = 37 * result
				+ (getJob() == null ? 0 : this.getJob().hashCode());
		return result;
	}

}