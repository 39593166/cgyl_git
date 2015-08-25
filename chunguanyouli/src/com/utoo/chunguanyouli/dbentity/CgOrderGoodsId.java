package com.utoo.chunguanyouli.dbentity;

/**
 * CgOrderGoodsId entity. @author MyEclipse Persistence Tools
 */

public class CgOrderGoodsId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 423855160104621303L;
	private Integer id;
	private String goodsname;
	private Integer goodsid;
	private String numstr;
	private String pic;
	private Integer num;
	private Double price;
	private Integer userid;
	private Integer gnid;

	// Constructors

	/** default constructor */
	public CgOrderGoodsId() {
	
	}

	/** full constructor */
	public CgOrderGoodsId(Integer id, String goodsname, Integer goodsid,
			String numstr, String pic, Integer num, Double price,
			Integer userid, Integer gnid) {
		this.id = id;
		this.goodsname = goodsname;
		this.goodsid = goodsid;
		this.numstr = numstr;
		this.pic = pic;
		this.num = num;
		this.price = price;
		this.userid = userid;
		this.gnid = gnid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoodsname() {
		return this.goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public Integer getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	public String getNumstr() {
		return this.numstr;
	}

	public void setNumstr(String numstr) {
		this.numstr = numstr;
	}

	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getGnid() {
		return this.gnid;
	}

	public void setGnid(Integer gnid) {
		this.gnid = gnid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CgOrderGoodsId))
			return false;
		CgOrderGoodsId castOther = (CgOrderGoodsId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getGoodsname() == castOther.getGoodsname()) || (this
						.getGoodsname() != null
						&& castOther.getGoodsname() != null && this
						.getGoodsname().equals(castOther.getGoodsname())))
				&& ((this.getGoodsid() == castOther.getGoodsid()) || (this
						.getGoodsid() != null && castOther.getGoodsid() != null && this
						.getGoodsid().equals(castOther.getGoodsid())))
				&& ((this.getNumstr() == castOther.getNumstr()) || (this
						.getNumstr() != null && castOther.getNumstr() != null && this
						.getNumstr().equals(castOther.getNumstr())))
				&& ((this.getPic() == castOther.getPic()) || (this.getPic() != null
						&& castOther.getPic() != null && this.getPic().equals(
						castOther.getPic())))
				&& ((this.getNum() == castOther.getNum()) || (this.getNum() != null
						&& castOther.getNum() != null && this.getNum().equals(
						castOther.getNum())))
				&& ((this.getPrice() == castOther.getPrice()) || (this
						.getPrice() != null && castOther.getPrice() != null && this
						.getPrice().equals(castOther.getPrice())))
				&& ((this.getUserid() == castOther.getUserid()) || (this
						.getUserid() != null && castOther.getUserid() != null && this
						.getUserid().equals(castOther.getUserid())))
				&& ((this.getGnid() == castOther.getGnid()) || (this.getGnid() != null
						&& castOther.getGnid() != null && this.getGnid()
						.equals(castOther.getGnid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getGoodsname() == null ? 0 : this.getGoodsname().hashCode());
		result = 37 * result
				+ (getGoodsid() == null ? 0 : this.getGoodsid().hashCode());
		result = 37 * result
				+ (getNumstr() == null ? 0 : this.getNumstr().hashCode());
		result = 37 * result
				+ (getPic() == null ? 0 : this.getPic().hashCode());
		result = 37 * result
				+ (getNum() == null ? 0 : this.getNum().hashCode());
		result = 37 * result
				+ (getPrice() == null ? 0 : this.getPrice().hashCode());
		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result
				+ (getGnid() == null ? 0 : this.getGnid().hashCode());
		return result;
	}

}