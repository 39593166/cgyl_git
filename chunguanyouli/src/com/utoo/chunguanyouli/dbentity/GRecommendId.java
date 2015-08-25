package com.utoo.chunguanyouli.dbentity;

import java.sql.Timestamp;

/**
 * GRecommendId entity. @author MyEclipse Persistence Tools
 */

public class GRecommendId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private Timestamp datesend;
	private String recommend1;
	private String recommend2;
	private String recommend3;
	private String recommend4;
	private Integer numget;
	private Integer goodsid;

	// Constructors

	/** default constructor */
	public GRecommendId() {
	}

	/** minimal constructor */
	public GRecommendId(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public GRecommendId(Integer id, Integer uid, Timestamp datesend,
			String recommend1, String recommend2, String recommend3,
			String recommend4, Integer numget, Integer goodsid) {
		this.id = id;
		this.uid = uid;
		this.datesend = datesend;
		this.recommend1 = recommend1;
		this.recommend2 = recommend2;
		this.recommend3 = recommend3;
		this.recommend4 = recommend4;
		this.numget = numget;
		this.goodsid = goodsid;
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

	public Timestamp getDatesend() {
		return this.datesend;
	}

	public void setDatesend(Timestamp datesend) {
		this.datesend = datesend;
	}

	public String getRecommend1() {
		return this.recommend1;
	}

	public void setRecommend1(String recommend1) {
		this.recommend1 = recommend1;
	}

	public String getRecommend2() {
		return this.recommend2;
	}

	public void setRecommend2(String recommend2) {
		this.recommend2 = recommend2;
	}

	public String getRecommend3() {
		return this.recommend3;
	}

	public void setRecommend3(String recommend3) {
		this.recommend3 = recommend3;
	}

	public String getRecommend4() {
		return this.recommend4;
	}

	public void setRecommend4(String recommend4) {
		this.recommend4 = recommend4;
	}

	public Integer getNumget() {
		return this.numget;
	}

	public void setNumget(Integer numget) {
		this.numget = numget;
	}

	public Integer getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GRecommendId))
			return false;
		GRecommendId castOther = (GRecommendId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getUid() == castOther.getUid()) || (this.getUid() != null
						&& castOther.getUid() != null && this.getUid().equals(
						castOther.getUid())))
				&& ((this.getDatesend() == castOther.getDatesend()) || (this
						.getDatesend() != null
						&& castOther.getDatesend() != null && this
						.getDatesend().equals(castOther.getDatesend())))
				&& ((this.getRecommend1() == castOther.getRecommend1()) || (this
						.getRecommend1() != null
						&& castOther.getRecommend1() != null && this
						.getRecommend1().equals(castOther.getRecommend1())))
				&& ((this.getRecommend2() == castOther.getRecommend2()) || (this
						.getRecommend2() != null
						&& castOther.getRecommend2() != null && this
						.getRecommend2().equals(castOther.getRecommend2())))
				&& ((this.getRecommend3() == castOther.getRecommend3()) || (this
						.getRecommend3() != null
						&& castOther.getRecommend3() != null && this
						.getRecommend3().equals(castOther.getRecommend3())))
				&& ((this.getRecommend4() == castOther.getRecommend4()) || (this
						.getRecommend4() != null
						&& castOther.getRecommend4() != null && this
						.getRecommend4().equals(castOther.getRecommend4())))
				&& ((this.getNumget() == castOther.getNumget()) || (this
						.getNumget() != null && castOther.getNumget() != null && this
						.getNumget().equals(castOther.getNumget())))
				&& ((this.getGoodsid() == castOther.getGoodsid()) || (this
						.getGoodsid() != null && castOther.getGoodsid() != null && this
						.getGoodsid().equals(castOther.getGoodsid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getUid() == null ? 0 : this.getUid().hashCode());
		result = 37 * result
				+ (getDatesend() == null ? 0 : this.getDatesend().hashCode());
		result = 37
				* result
				+ (getRecommend1() == null ? 0 : this.getRecommend1()
						.hashCode());
		result = 37
				* result
				+ (getRecommend2() == null ? 0 : this.getRecommend2()
						.hashCode());
		result = 37
				* result
				+ (getRecommend3() == null ? 0 : this.getRecommend3()
						.hashCode());
		result = 37
				* result
				+ (getRecommend4() == null ? 0 : this.getRecommend4()
						.hashCode());
		result = 37 * result
				+ (getNumget() == null ? 0 : this.getNumget().hashCode());
		result = 37 * result
				+ (getGoodsid() == null ? 0 : this.getGoodsid().hashCode());
		return result;
	}

}