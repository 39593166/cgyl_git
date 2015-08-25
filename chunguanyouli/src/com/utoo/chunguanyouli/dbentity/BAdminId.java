package com.utoo.chunguanyouli.dbentity;

/**
 * BAdminId entity. @author MyEclipse Persistence Tools
 */

public class BAdminId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String userpass;
	private Integer greedid;
	private Boolean islock;

	// Constructors

	/** default constructor */
	public BAdminId() {
	}

	/** full constructor */
	public BAdminId(Integer id, String username, String userpass,
			Integer greedid, Boolean islock) {
		this.id = id;
		this.username = username;
		this.userpass = userpass;
		this.greedid = greedid;
		this.islock = islock;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return this.userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public Integer getGreedid() {
		return this.greedid;
	}

	public void setGreedid(Integer greedid) {
		this.greedid = greedid;
	}

	public Boolean getIslock() {
		return this.islock;
	}

	public void setIslock(Boolean islock) {
		this.islock = islock;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BAdminId))
			return false;
		BAdminId castOther = (BAdminId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getUsername() == castOther.getUsername()) || (this
						.getUsername() != null
						&& castOther.getUsername() != null && this
						.getUsername().equals(castOther.getUsername())))
				&& ((this.getUserpass() == castOther.getUserpass()) || (this
						.getUserpass() != null
						&& castOther.getUserpass() != null && this
						.getUserpass().equals(castOther.getUserpass())))
				&& ((this.getGreedid() == castOther.getGreedid()) || (this
						.getGreedid() != null && castOther.getGreedid() != null && this
						.getGreedid().equals(castOther.getGreedid())))
				&& ((this.getIslock() == castOther.getIslock()) || (this
						.getIslock() != null && castOther.getIslock() != null && this
						.getIslock().equals(castOther.getIslock())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getUsername() == null ? 0 : this.getUsername().hashCode());
		result = 37 * result
				+ (getUserpass() == null ? 0 : this.getUserpass().hashCode());
		result = 37 * result
				+ (getGreedid() == null ? 0 : this.getGreedid().hashCode());
		result = 37 * result
				+ (getIslock() == null ? 0 : this.getIslock().hashCode());
		return result;
	}

}