package com.utoo.chunguanyouli.dbentity;

import java.util.ArrayList;

/**
 * CgOrderId entity. @author MyEclipse Persistence Tools
 */

public class CgOrderId implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -194101987431684275L;
	public static int STATU_USER_SUBMIT = 0;// 提交(待付款
	public static int STATU_PAYED = 1;// 支付成功（已支付）
	public static int STATU_WAITTING_APPEND = 10;// 财务确认,等待发货
	public static int STATU_APPENDDING = 3;// 已发货
	public static int STATU_OVER = 5;// 已收货
	public static int STATU_APPLY_RETURN_PAY = -1;
	public static int STATU_RETURNED_PAY = -8;
	public static int STATU_APPLY_RETURN_GOODS = -3;
	public static int STATU_RETURNED_GOODS = -9;
	public static int STATU_DELETED = -7;

	public static int TYPE_ALL = 1;// 全部
	public static int TYPE_NO_PAY = 2;// 0未付款
	public static int TYPE_WAIT_APPEND = 3;// 1,2、已付款
	public static int TYPE_WAIT_COLLECT = 4;// 3 已发货
	public static int TYPE_WAIT_COMMEND = 5;// 4 已完成
	// Fields

	private Integer id;
	private String dateset;
	private Double goodsprice;
	private Double postage;
	private Integer paytype;
	private String bankstr;
	private String rearea;
	private String readdress;
	private String rezip;
	private String receiver;
	private String rephone;
	private String remobile;
	private Integer deliveryid;
	private String ofrom;
	private String numstr;
	private Integer uid;
	private Integer cuid;
	private String wnum;
	// //
	private int nowstate;
	private int type;
	private ArrayList<CgOrderGoodsId> goodslist;

	// Constructors

	/** default constructor */
	public CgOrderId() {
	}

	/** minimal constructor */
	public CgOrderId(Integer id, String dateset, Double goodsprice,
			Double postage, Integer paytype, String bankstr, String rearea,
			String readdress, String rezip, String receiver, String rephone,
			String remobile, Integer deliveryid, String ofrom) {
		this.id = id;
		this.dateset = dateset;
		this.goodsprice = goodsprice;
		this.postage = postage;
		this.paytype = paytype;
		this.bankstr = bankstr;
		this.rearea = rearea;
		this.readdress = readdress;
		this.rezip = rezip;
		this.receiver = receiver;
		this.rephone = rephone;
		this.remobile = remobile;
		this.deliveryid = deliveryid;
		this.ofrom = ofrom;
	}

	/** full constructor */
	public CgOrderId(Integer id, String dateset, Double goodsprice,
			Double postage, Integer paytype, String bankstr, String rearea,
			String readdress, String rezip, String receiver, String rephone,
			String remobile, Integer deliveryid, String ofrom, String numstr,
			Integer uid) {
		this.id = id;
		this.dateset = dateset;
		this.goodsprice = goodsprice;
		this.postage = postage;
		this.paytype = paytype;
		this.bankstr = bankstr;
		this.rearea = rearea;
		this.readdress = readdress;
		this.rezip = rezip;
		this.receiver = receiver;
		this.rephone = rephone;
		this.remobile = remobile;
		this.deliveryid = deliveryid;
		this.ofrom = ofrom;
		this.numstr = numstr;
		this.uid = uid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public int getNowState() {
		return nowstate;
	}

	public void setNowState(int nowstate) {
		this.nowstate = nowstate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDateset() {
		return this.dateset;
	}

	public void setDateset(String dateset) {
		this.dateset = dateset;
	}

	public Double getGoodsprice() {
		return this.goodsprice;
	}

	public void setGoodsprice(Double goodsprice) {
		this.goodsprice = goodsprice;
	}

	public Double getPostage() {
		return this.postage;
	}

	public void setPostage(Double postage) {
		this.postage = postage;
	}

	public Integer getPaytype() {
		return this.paytype;
	}

	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}

	public String getBankstr() {
		return this.bankstr;
	}

	public void setBankstr(String bankstr) {
		this.bankstr = bankstr;
	}

	public String getRearea() {
		return this.rearea;
	}

	public void setRearea(String rearea) {
		this.rearea = rearea;
	}

	public String getReaddress() {
		return this.readdress;
	}

	public void setReaddress(String readdress) {
		this.readdress = readdress;
	}

	public String getRezip() {
		return this.rezip;
	}

	public void setRezip(String rezip) {
		this.rezip = rezip;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getRephone() {
		return this.rephone;
	}

	public void setRephone(String rephone) {
		this.rephone = rephone;
	}

	public String getRemobile() {
		return this.remobile;
	}

	public void setRemobile(String remobile) {
		this.remobile = remobile;
	}

	public Integer getDeliveryid() {
		return this.deliveryid;
	}

	public void setDeliveryid(Integer deliveryid) {
		this.deliveryid = deliveryid;
	}

	public String getOfrom() {
		return this.ofrom;
	}

	public void setOfrom(String ofrom) {
		this.ofrom = ofrom;
	}

	public String getNumstr() {
		return this.numstr;
	}

	public void setNumstr(String numstr) {
		this.numstr = numstr;
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
		if (!(other instanceof CgOrderId))
			return false;
		CgOrderId castOther = (CgOrderId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getDateset() == castOther.getDateset()) || (this
						.getDateset() != null && castOther.getDateset() != null && this
						.getDateset().equals(castOther.getDateset())))
				&& ((this.getGoodsprice() == castOther.getGoodsprice()) || (this
						.getGoodsprice() != null
						&& castOther.getGoodsprice() != null && this
						.getGoodsprice().equals(castOther.getGoodsprice())))
				&& ((this.getPostage() == castOther.getPostage()) || (this
						.getPostage() != null && castOther.getPostage() != null && this
						.getPostage().equals(castOther.getPostage())))
				&& ((this.getPaytype() == castOther.getPaytype()) || (this
						.getPaytype() != null && castOther.getPaytype() != null && this
						.getPaytype().equals(castOther.getPaytype())))
				&& ((this.getBankstr() == castOther.getBankstr()) || (this
						.getBankstr() != null && castOther.getBankstr() != null && this
						.getBankstr().equals(castOther.getBankstr())))
				&& ((this.getRearea() == castOther.getRearea()) || (this
						.getRearea() != null && castOther.getRearea() != null && this
						.getRearea().equals(castOther.getRearea())))
				&& ((this.getReaddress() == castOther.getReaddress()) || (this
						.getReaddress() != null
						&& castOther.getReaddress() != null && this
						.getReaddress().equals(castOther.getReaddress())))
				&& ((this.getRezip() == castOther.getRezip()) || (this
						.getRezip() != null && castOther.getRezip() != null && this
						.getRezip().equals(castOther.getRezip())))
				&& ((this.getReceiver() == castOther.getReceiver()) || (this
						.getReceiver() != null
						&& castOther.getReceiver() != null && this
						.getReceiver().equals(castOther.getReceiver())))
				&& ((this.getRephone() == castOther.getRephone()) || (this
						.getRephone() != null && castOther.getRephone() != null && this
						.getRephone().equals(castOther.getRephone())))
				&& ((this.getRemobile() == castOther.getRemobile()) || (this
						.getRemobile() != null
						&& castOther.getRemobile() != null && this
						.getRemobile().equals(castOther.getRemobile())))
				&& ((this.getDeliveryid() == castOther.getDeliveryid()) || (this
						.getDeliveryid() != null
						&& castOther.getDeliveryid() != null && this
						.getDeliveryid().equals(castOther.getDeliveryid())))
				&& ((this.getOfrom() == castOther.getOfrom()) || (this
						.getOfrom() != null && castOther.getOfrom() != null && this
						.getOfrom().equals(castOther.getOfrom())))
				&& ((this.getNumstr() == castOther.getNumstr()) || (this
						.getNumstr() != null && castOther.getNumstr() != null && this
						.getNumstr().equals(castOther.getNumstr())))
				&& ((this.getUid() == castOther.getUid()) || (this.getUid() != null
						&& castOther.getUid() != null && this.getUid().equals(
						castOther.getUid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getDateset() == null ? 0 : this.getDateset().hashCode());
		result = 37
				* result
				+ (getGoodsprice() == null ? 0 : this.getGoodsprice()
						.hashCode());
		result = 37 * result
				+ (getPostage() == null ? 0 : this.getPostage().hashCode());
		result = 37 * result
				+ (getPaytype() == null ? 0 : this.getPaytype().hashCode());
		result = 37 * result
				+ (getBankstr() == null ? 0 : this.getBankstr().hashCode());
		result = 37 * result
				+ (getRearea() == null ? 0 : this.getRearea().hashCode());
		result = 37 * result
				+ (getReaddress() == null ? 0 : this.getReaddress().hashCode());
		result = 37 * result
				+ (getRezip() == null ? 0 : this.getRezip().hashCode());
		result = 37 * result
				+ (getReceiver() == null ? 0 : this.getReceiver().hashCode());
		result = 37 * result
				+ (getRephone() == null ? 0 : this.getRephone().hashCode());
		result = 37 * result
				+ (getRemobile() == null ? 0 : this.getRemobile().hashCode());
		result = 37
				* result
				+ (getDeliveryid() == null ? 0 : this.getDeliveryid()
						.hashCode());
		result = 37 * result
				+ (getOfrom() == null ? 0 : this.getOfrom().hashCode());
		result = 37 * result
				+ (getNumstr() == null ? 0 : this.getNumstr().hashCode());
		result = 37 * result
				+ (getUid() == null ? 0 : this.getUid().hashCode());
		return result;
	}

	public ArrayList<CgOrderGoodsId> getGoodslist() {
		return goodslist;
	}

	public void setGoodslist(ArrayList<CgOrderGoodsId> goodslist) {
		this.goodslist = goodslist;
	}

	public Integer getCuid() {
		return cuid;
	}

	public void setCuid(Integer cuid) {
		this.cuid = cuid;
	}

	public String getWnum() {
		return wnum;
	}

	public void setWnum(String wnum) {
		this.wnum = wnum;
	}

}