package com.utoo.chunguanyouli.dbentity;

/**
 * CgRecommendGoodsId entity. @author MyEclipse Persistence Tools
 */

public class CgRecommendGoodsId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer gid;
	private String pic;
	private String name;
	private Integer numstar;
	private String recommend;
	private Integer num1;
	private Integer num2;

	// Constructors

	/** default constructor */
	public CgRecommendGoodsId() {
	}

	/** minimal constructor */
	public CgRecommendGoodsId(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public CgRecommendGoodsId(Integer id, Integer gid, String pic, String name,
			Integer numstar, String recommend, Integer num1, Integer num2) {
		this.id = id;
		this.gid = gid;
		this.pic = pic;
		this.name = name;
		this.numstar = numstar;
		this.recommend = recommend;
		this.num1 = num1;
		this.num2 = num2;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGid() {
		return this.gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumstar() {
		return this.numstar;
	}

	public void setNumstar(Integer numstar) {
		this.numstar = numstar;
	}

	public String getRecommend() {
		return this.recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public Integer getNum1() {
		return this.num1;
	}

	public void setNum1(Integer num1) {
		this.num1 = num1;
	}

	public Integer getNum2() {
		return this.num2;
	}

	public void setNum2(Integer num2) {
		this.num2 = num2;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CgRecommendGoodsId))
			return false;
		CgRecommendGoodsId castOther = (CgRecommendGoodsId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getGid() == castOther.getGid()) || (this.getGid() != null
						&& castOther.getGid() != null && this.getGid().equals(
						castOther.getGid())))
				&& ((this.getPic() == castOther.getPic()) || (this.getPic() != null
						&& castOther.getPic() != null && this.getPic().equals(
						castOther.getPic())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getNumstar() == castOther.getNumstar()) || (this
						.getNumstar() != null && castOther.getNumstar() != null && this
						.getNumstar().equals(castOther.getNumstar())))
				&& ((this.getRecommend() == castOther.getRecommend()) || (this
						.getRecommend() != null
						&& castOther.getRecommend() != null && this
						.getRecommend().equals(castOther.getRecommend())))
				&& ((this.getNum1() == castOther.getNum1()) || (this.getNum1() != null
						&& castOther.getNum1() != null && this.getNum1()
						.equals(castOther.getNum1())))
				&& ((this.getNum2() == castOther.getNum2()) || (this.getNum2() != null
						&& castOther.getNum2() != null && this.getNum2()
						.equals(castOther.getNum2())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getGid() == null ? 0 : this.getGid().hashCode());
		result = 37 * result
				+ (getPic() == null ? 0 : this.getPic().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getNumstar() == null ? 0 : this.getNumstar().hashCode());
		result = 37 * result
				+ (getRecommend() == null ? 0 : this.getRecommend().hashCode());
		result = 37 * result
				+ (getNum1() == null ? 0 : this.getNum1().hashCode());
		result = 37 * result
				+ (getNum2() == null ? 0 : this.getNum2().hashCode());
		return result;
	}

}