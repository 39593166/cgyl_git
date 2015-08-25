package com.utoo.chunguanyouli.dbentity;

import java.util.List;

import com.utoo.chunguanyouli.server.ClientConfigs;

/**
 * CgGoodsId entity. @author MyEclipse Persistence Tools
 */

public class CgGoodsId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 450929620071212879L;
	private int id;
	private String name;
	private String pic;
	private Double price;
	private int numhave;
	private int numsell;
	private int uid;//
	private int cid;//
	private Double pricenot;
	private String datesend;
	private String deposit;
	private String exp;
	private String additive;
	private String pack;
	private String weight;
	private String organic;
	private String content;
	private int tid;
	private boolean islock;
	private boolean isdel;
	private String fromuser;
	private List<Pics> pic1;
	private List<Pics> pic2;
	private List<Pics> pic3;

	private boolean istj;// 是否主页推荐
	private boolean utj;// 是否店铺推荐
	private int buyNum;
	private boolean select;

	// Constructors

	/** default constructor */
	public CgGoodsId() {
	}

	/** minimal constructor */
	public CgGoodsId(int id, int tid) {
		this.id = id;
		this.tid = tid;
	}

	public CgGoodsId(int id, String name, String pic, Double price,
			int numhave, int numsell, int uid, int cid, Double pricenot,
			String datesend, String deposit, String exp, String additive,
			String pack, String weight, String organic, String content,
			int tid, boolean islock, boolean isdel, int buyNum, boolean select,
			String fromuser, List<Pics> pic1, List<Pics> pic2, List<Pics> pic3) {
		super();
		this.id = id;
		this.name = name;
		this.pic = pic;
		this.price = price;
		this.numhave = numhave;
		this.numsell = numsell;
		this.uid = uid;
		this.cid = cid;
		this.pricenot = pricenot;
		this.datesend = datesend;
		this.deposit = deposit;
		this.exp = exp;
		this.additive = additive;
		this.pack = pack;
		this.weight = weight;
		this.organic = organic;
		this.content = content;
		this.tid = tid;
		this.islock = islock;
		this.isdel = isdel;
		this.buyNum = buyNum;
		this.select = select;
		this.fromuser = fromuser;
		this.pic1 = pic1;
		this.pic2 = pic2;
		this.pic3 = pic3;
	}

	public CgGoodsId copy() {
		CgGoodsId cg = new CgGoodsId(id, name, pic, price, numhave, numsell,
				uid, cid, pricenot, datesend, deposit, exp, additive, pack,
				weight, organic, content, tid, islock, isdel, buyNum, select,
				fromuser, null, null, null);
		return cg;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		if (pic == null) {
			return "";
		}
		if (pic.startsWith("http"))
			return pic;
		else
			return ClientConfigs.PICHOST + pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getNumhave() {
		return this.numhave;
	}

	public void setNumhave(int numhave) {
		this.numhave = numhave;
	}

	public int getNumsell() {
		return this.numsell;
	}

	public void setNumsell(int numsell) {
		this.numsell = numsell;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getCid() {
		return this.cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public Double getPricenot() {
		return this.pricenot;
	}

	public void setPricenot(Double pricenot) {
		this.pricenot = pricenot;
	}

	public String getDatesend() {
		return this.datesend;
	}

	public void setDatesend(String datesend) {
		this.datesend = datesend;
	}

	public String getDeposit() {
		return this.deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public String getExp() {
		return this.exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getAdditive() {
		return this.additive;
	}

	public void setAdditive(String additive) {
		this.additive = additive;
	}

	public String getPack() {
		return this.pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getOrganic() {
		return this.organic;
	}

	public void setOrganic(String organic) {
		this.organic = organic;
	}

	public int getTid() {
		return this.tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public boolean getIslock() {
		return islock;
	}

	public void setIslock(boolean islock) {
		this.islock = islock;
	}

	public boolean isIsdel() {
		return isdel;
	}

	public void setIsdel(boolean isdel) {
		this.isdel = isdel;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Pics> getPic1() {
		return pic1;
	}

	public void setPic1(List<Pics> pic1) {
		this.pic1 = pic1;
	}

	public List<Pics> getPic2() {
		return pic2;
	}

	public void setPic2(List<Pics> pic2) {
		this.pic2 = pic2;
	}

	public List<Pics> getPic3() {
		return pic3;
	}

	public void setPic3(List<Pics> pic3) {
		this.pic3 = pic3;
	}

	public String getFromuser() {
		return fromuser;
	}

	public void setFromuser(String fromuser) {
		this.fromuser = fromuser;
	}

	public boolean isIstj() {
		return istj;
	}

	public void setIstj(boolean istj) {
		this.istj = istj;
	}

	public boolean isUtj() {
		return utj;
	}

	public void setUtj(boolean utj) {
		this.utj = utj;
	}

}