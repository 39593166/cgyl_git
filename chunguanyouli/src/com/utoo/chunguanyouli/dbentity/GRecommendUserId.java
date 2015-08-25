package com.utoo.chunguanyouli.dbentity;

/**
 * GRecommendUserId entity. @author MyEclipse Persistence Tools
 */

public class GRecommendUserId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String realname;
	private String zw;
	private String pic;

	// Constructors

	/** default constructor */
	public GRecommendUserId() {
	}

	/** minimal constructor */
	public GRecommendUserId(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public GRecommendUserId(Integer id, String realname, String zw, String pic) {
		this.id = id;
		this.realname = realname;
		this.zw = zw;
		this.pic = pic;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getZw() {
		return this.zw;
	}

	public void setZw(String zw) {
		this.zw = zw;
	}

	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GRecommendUserId))
			return false;
		GRecommendUserId castOther = (GRecommendUserId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getRealname() == castOther.getRealname()) || (this
						.getRealname() != null
						&& castOther.getRealname() != null && this
						.getRealname().equals(castOther.getRealname())))
				&& ((this.getZw() == castOther.getZw()) || (this.getZw() != null
						&& castOther.getZw() != null && this.getZw().equals(
						castOther.getZw())))
				&& ((this.getPic() == castOther.getPic()) || (this.getPic() != null
						&& castOther.getPic() != null && this.getPic().equals(
						castOther.getPic())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getRealname() == null ? 0 : this.getRealname().hashCode());
		result = 37 * result + (getZw() == null ? 0 : this.getZw().hashCode());
		result = 37 * result
				+ (getPic() == null ? 0 : this.getPic().hashCode());
		return result;
	}

}