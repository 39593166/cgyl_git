package com.utoo.chunguanyouli.dbentity;

import com.utoo.chunguanyouli.server.ClientConfigs;


/**
 * CgNewsId entity. @author MyEclipse Persistence Tools
 */

public class CgNewsId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6423737442622522624L;
	private int id;
	private String title;
	private String content;
	private String datesend;
	private String pic;
	private int uid;
	private int cid;
	private int tid;

	// Constructors

	/** default constructor */
	public CgNewsId() {
	}

	/** minimal constructor */
	public CgNewsId(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public CgNewsId(Integer id, String title, String content,
			String datesend, String pic, Integer uid) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.datesend = datesend;
		this.pic = pic;
		this.uid = uid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDatesend() {
		return this.datesend;
	}

	public void setDatesend(String datesend) {
		this.datesend = datesend;
	}

	public String getPic() {
		if(pic.startsWith("http")){
			return pic;
		}else {
			return ClientConfigs.PICHOST+pic;
		}
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CgNewsId))
			return false;
		CgNewsId castOther = (CgNewsId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getTitle() == castOther.getTitle()) || (this
						.getTitle() != null && castOther.getTitle() != null && this
						.getTitle().equals(castOther.getTitle())))
				&& ((this.getContent() == castOther.getContent()) || (this
						.getContent() != null && castOther.getContent() != null && this
						.getContent().equals(castOther.getContent())))
				&& ((this.getDatesend() == castOther.getDatesend()) || (this
						.getDatesend() != null
						&& castOther.getDatesend() != null && this
						.getDatesend().equals(castOther.getDatesend())))
				&& ((this.getPic() == castOther.getPic()) || (this.getPic() != null
						&& castOther.getPic() != null && this.getPic().equals(
						castOther.getPic())))
				&& ((this.getUid() == castOther.getUid()) || (this.getUid() != null
						&& castOther.getUid() != null && this.getUid().equals(
						castOther.getUid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getTitle() == null ? 0 : this.getTitle().hashCode());
		result = 37 * result
				+ (getContent() == null ? 0 : this.getContent().hashCode());
		result = 37 * result
				+ (getDatesend() == null ? 0 : this.getDatesend().hashCode());
		result = 37 * result
				+ (getPic() == null ? 0 : this.getPic().hashCode());
		result = 37 * result
				+ (getUid() == null ? 0 : this.getUid().hashCode());
		return result;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

}