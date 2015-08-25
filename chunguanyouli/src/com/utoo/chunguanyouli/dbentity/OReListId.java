package com.utoo.chunguanyouli.dbentity;

import java.sql.Timestamp;

/**
 * OReListId entity. @author MyEclipse Persistence Tools
 */

public class OReListId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer goodsid;
	private Integer ogid;
	private String reply;
	private Timestamp dateset;
	private Integer userid;
	private Integer star;
	private Integer numlike;
	private Integer numunlike;
	private Integer typeid;
	private String tag;

	// Constructors

	/** default constructor */
	public OReListId() {
	}

	/** minimal constructor */
	public OReListId(Integer id, Integer goodsid, Integer ogid, String reply,
			Timestamp dateset, Integer userid, Integer star, Integer numlike,
			Integer numunlike, Integer typeid) {
		this.id = id;
		this.goodsid = goodsid;
		this.ogid = ogid;
		this.reply = reply;
		this.dateset = dateset;
		this.userid = userid;
		this.star = star;
		this.numlike = numlike;
		this.numunlike = numunlike;
		this.typeid = typeid;
	}

	/** full constructor */
	public OReListId(Integer id, Integer goodsid, Integer ogid, String reply,
			Timestamp dateset, Integer userid, Integer star, Integer numlike,
			Integer numunlike, Integer typeid, String tag) {
		this.id = id;
		this.goodsid = goodsid;
		this.ogid = ogid;
		this.reply = reply;
		this.dateset = dateset;
		this.userid = userid;
		this.star = star;
		this.numlike = numlike;
		this.numunlike = numunlike;
		this.typeid = typeid;
		this.tag = tag;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	public Integer getOgid() {
		return this.ogid;
	}

	public void setOgid(Integer ogid) {
		this.ogid = ogid;
	}

	public String getReply() {
		return this.reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public Timestamp getDateset() {
		return this.dateset;
	}

	public void setDateset(Timestamp dateset) {
		this.dateset = dateset;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getStar() {
		return this.star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public Integer getNumlike() {
		return this.numlike;
	}

	public void setNumlike(Integer numlike) {
		this.numlike = numlike;
	}

	public Integer getNumunlike() {
		return this.numunlike;
	}

	public void setNumunlike(Integer numunlike) {
		this.numunlike = numunlike;
	}

	public Integer getTypeid() {
		return this.typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OReListId))
			return false;
		OReListId castOther = (OReListId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getGoodsid() == castOther.getGoodsid()) || (this
						.getGoodsid() != null && castOther.getGoodsid() != null && this
						.getGoodsid().equals(castOther.getGoodsid())))
				&& ((this.getOgid() == castOther.getOgid()) || (this.getOgid() != null
						&& castOther.getOgid() != null && this.getOgid()
						.equals(castOther.getOgid())))
				&& ((this.getReply() == castOther.getReply()) || (this
						.getReply() != null && castOther.getReply() != null && this
						.getReply().equals(castOther.getReply())))
				&& ((this.getDateset() == castOther.getDateset()) || (this
						.getDateset() != null && castOther.getDateset() != null && this
						.getDateset().equals(castOther.getDateset())))
				&& ((this.getUserid() == castOther.getUserid()) || (this
						.getUserid() != null && castOther.getUserid() != null && this
						.getUserid().equals(castOther.getUserid())))
				&& ((this.getStar() == castOther.getStar()) || (this.getStar() != null
						&& castOther.getStar() != null && this.getStar()
						.equals(castOther.getStar())))
				&& ((this.getNumlike() == castOther.getNumlike()) || (this
						.getNumlike() != null && castOther.getNumlike() != null && this
						.getNumlike().equals(castOther.getNumlike())))
				&& ((this.getNumunlike() == castOther.getNumunlike()) || (this
						.getNumunlike() != null
						&& castOther.getNumunlike() != null && this
						.getNumunlike().equals(castOther.getNumunlike())))
				&& ((this.getTypeid() == castOther.getTypeid()) || (this
						.getTypeid() != null && castOther.getTypeid() != null && this
						.getTypeid().equals(castOther.getTypeid())))
				&& ((this.getTag() == castOther.getTag()) || (this.getTag() != null
						&& castOther.getTag() != null && this.getTag().equals(
						castOther.getTag())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getGoodsid() == null ? 0 : this.getGoodsid().hashCode());
		result = 37 * result
				+ (getOgid() == null ? 0 : this.getOgid().hashCode());
		result = 37 * result
				+ (getReply() == null ? 0 : this.getReply().hashCode());
		result = 37 * result
				+ (getDateset() == null ? 0 : this.getDateset().hashCode());
		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result
				+ (getStar() == null ? 0 : this.getStar().hashCode());
		result = 37 * result
				+ (getNumlike() == null ? 0 : this.getNumlike().hashCode());
		result = 37 * result
				+ (getNumunlike() == null ? 0 : this.getNumunlike().hashCode());
		result = 37 * result
				+ (getTypeid() == null ? 0 : this.getTypeid().hashCode());
		result = 37 * result
				+ (getTag() == null ? 0 : this.getTag().hashCode());
		return result;
	}

}