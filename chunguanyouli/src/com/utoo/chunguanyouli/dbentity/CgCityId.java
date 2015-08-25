package com.utoo.chunguanyouli.dbentity;

import com.utoo.chunguanyouli.server.ClientConfigs;

/**
 * CgCityId entity. @author MyEclipse Persistence Tools
 */

public class CgCityId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3872047773058435093L;
	private int id;
	private int cid;
	private String name;
	private int pid;
	private String content;
	private String effect;
	private int numhu;
	private int numpro;
	private int numorder;
	private int numgoods;
	private int uid;
	private String datesend;
	private String pic;

	// Constructors

	/** default constructor */
	public CgCityId() {
	}

	/** minimal constructor */
	public CgCityId(int id) {
		this.id = id;
	}

	/** full constructor */
	public CgCityId(int id, int cid, int pid, String content,
			String effect, int numhu, int numpro, int numorder,
			int numgoods, int uid, String datesend) {
		this.id = id;
		this.cid = cid;
		this.pid = pid;
		this.content = content;
		this.effect = effect;
		this.numhu = numhu;
		this.numpro = numpro;
		this.numorder = numorder;
		this.numgoods = numgoods;
		this.uid = uid;
		this.datesend = datesend;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCid() {
		return this.cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getPid() {
		return this.pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEffect() {
		return this.effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public int getNumhu() {
		return this.numhu;
	}

	public void setNumhu(int numhu) {
		this.numhu = numhu;
	}

	public int getNumpro() {
		return this.numpro;
	}

	public void setNumpro(int numpro) {
		this.numpro = numpro;
	}

	public int getNumorder() {
		return this.numorder;
	}

	public void setNumorder(int numorder) {
		this.numorder = numorder;
	}

	public int getNumgoods() {
		return this.numgoods;
	}

	public void setNumgoods(int numgoods) {
		this.numgoods = numgoods;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getDatesend() {
		return this.datesend;
	}

	public void setDatesend(String datesend) {
		this.datesend = datesend;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		if (pic.startsWith("http"))
			return pic;
		else
			return ClientConfigs.PICHOST + pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

}