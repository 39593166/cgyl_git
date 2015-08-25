package com.utoo.chunguanyouli.dbentity;

import java.sql.Timestamp;

import com.utoo.chunguanyouli.server.ClientConfigs;

/**
 * NNewsInfoId entity. @author MyEclipse Persistence Tools
 */

public class NNewsInfoId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private Integer tid;
	private Timestamp datesend;
	private String nfrom;
	private String content;
	private String pic;

	// Constructors

	/** default constructor */
	public NNewsInfoId() {
	}

	/** minimal constructor */
	public NNewsInfoId(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public NNewsInfoId(Integer id, String title, Integer tid,
			Timestamp datesend, String nfrom, String content, String pic) {
		this.id = id;
		this.title = title;
		this.tid = tid;
		this.datesend = datesend;
		this.nfrom = nfrom;
		this.content = content;
		this.pic = pic;
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

	public Integer getTid() {
		return this.tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Timestamp getDatesend() {
		return this.datesend;
	}

	public void setDatesend(Timestamp datesend) {
		this.datesend = datesend;
	}

	public String getNfrom() {
		return this.nfrom;
	}

	public void setNfrom(String nfrom) {
		this.nfrom = nfrom;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPic() {
		if (pic.startsWith("http")) {
			return pic;
		} else {
			return ClientConfigs.PICHOST + pic;
		}
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof NNewsInfoId))
			return false;
		NNewsInfoId castOther = (NNewsInfoId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getTitle() == castOther.getTitle()) || (this
						.getTitle() != null && castOther.getTitle() != null && this
						.getTitle().equals(castOther.getTitle())))
				&& ((this.getTid() == castOther.getTid()) || (this.getTid() != null
						&& castOther.getTid() != null && this.getTid().equals(
						castOther.getTid())))
				&& ((this.getDatesend() == castOther.getDatesend()) || (this
						.getDatesend() != null
						&& castOther.getDatesend() != null && this
						.getDatesend().equals(castOther.getDatesend())))
				&& ((this.getNfrom() == castOther.getNfrom()) || (this
						.getNfrom() != null && castOther.getNfrom() != null && this
						.getNfrom().equals(castOther.getNfrom())))
				&& ((this.getContent() == castOther.getContent()) || (this
						.getContent() != null && castOther.getContent() != null && this
						.getContent().equals(castOther.getContent())))
				&& ((this.getPic() == castOther.getPic()) || (this.getPic() != null
						&& castOther.getPic() != null && this.getPic().equals(
						castOther.getPic())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getTitle() == null ? 0 : this.getTitle().hashCode());
		result = 37 * result
				+ (getTid() == null ? 0 : this.getTid().hashCode());
		result = 37 * result
				+ (getDatesend() == null ? 0 : this.getDatesend().hashCode());
		result = 37 * result
				+ (getNfrom() == null ? 0 : this.getNfrom().hashCode());
		result = 37 * result
				+ (getContent() == null ? 0 : this.getContent().hashCode());
		result = 37 * result
				+ (getPic() == null ? 0 : this.getPic().hashCode());
		return result;
	}

}