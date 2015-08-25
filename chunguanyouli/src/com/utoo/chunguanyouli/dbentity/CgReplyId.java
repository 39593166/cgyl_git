package com.utoo.chunguanyouli.dbentity;


/**
 * CgReplyId entity. @author MyEclipse Persistence Tools
 */

public class CgReplyId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8385692206246711475L;
	private Integer id;
	private Integer uid;
	private Integer cid;
	private String content;
	private String uname;
	private Integer reuid;
	private String datesend;

	// Constructors

	/** default constructor */
	public CgReplyId() {
	}

	/** minimal constructor */
	public CgReplyId(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public CgReplyId(Integer id, Integer uid, Integer cid, String content,
			Integer reuid, String datesend) {
		this.id = id;
		this.uid = uid;
		this.cid = cid;
		this.content = content;
		this.reuid = reuid;
		this.datesend = datesend;
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

	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getReuid() {
		return this.reuid;
	}

	public void setReuid(Integer reuid) {
		this.reuid = reuid;
	}

	public String getDatesend() {
		return this.datesend;
	}

	public void setDatesend(String datesend) {
		this.datesend = datesend;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CgReplyId))
			return false;
		CgReplyId castOther = (CgReplyId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getUid() == castOther.getUid()) || (this.getUid() != null
						&& castOther.getUid() != null && this.getUid().equals(
						castOther.getUid())))
				&& ((this.getCid() == castOther.getCid()) || (this.getCid() != null
						&& castOther.getCid() != null && this.getCid().equals(
						castOther.getCid())))
				&& ((this.getContent() == castOther.getContent()) || (this
						.getContent() != null && castOther.getContent() != null && this
						.getContent().equals(castOther.getContent())))
				&& ((this.getReuid() == castOther.getReuid()) || (this
						.getReuid() != null && castOther.getReuid() != null && this
						.getReuid().equals(castOther.getReuid())))
				&& ((this.getDatesend() == castOther.getDatesend()) || (this
						.getDatesend() != null
						&& castOther.getDatesend() != null && this
						.getDatesend().equals(castOther.getDatesend())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getUid() == null ? 0 : this.getUid().hashCode());
		result = 37 * result
				+ (getCid() == null ? 0 : this.getCid().hashCode());
		result = 37 * result
				+ (getContent() == null ? 0 : this.getContent().hashCode());
		result = 37 * result
				+ (getReuid() == null ? 0 : this.getReuid().hashCode());
		result = 37 * result
				+ (getDatesend() == null ? 0 : this.getDatesend().hashCode());
		return result;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

}