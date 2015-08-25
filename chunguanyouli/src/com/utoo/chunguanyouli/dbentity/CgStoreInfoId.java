package com.utoo.chunguanyouli.dbentity;

import com.utoo.chunguanyouli.server.ClientConfigs;

/**
 * CgStoreInfoId entity. @author MyEclipse Persistence Tools
 */

public class CgStoreInfoId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3619976939031483110L;
	private Integer id;
	private String name;
	private Integer uid;
	private String area;
	private String domain;
	private String datastart;
	private String pic;

	// Constructors

	/** default constructor */
	public CgStoreInfoId() {
	}

	/** minimal constructor */
	public CgStoreInfoId(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public CgStoreInfoId(Integer id, String name, Integer uid, String area,
			String domain, String datastart) {
		this.id = id;
		this.name = name;
		this.uid = uid;
		this.area = area;
		this.domain = domain;
		this.datastart = datastart;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDatastart() {
		return this.datastart;
	}

	public void setDatastart(String datastart) {
		this.datastart = datastart;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CgStoreInfoId))
			return false;
		CgStoreInfoId castOther = (CgStoreInfoId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getUid() == castOther.getUid()) || (this.getUid() != null
						&& castOther.getUid() != null && this.getUid().equals(
						castOther.getUid())))
				&& ((this.getArea() == castOther.getArea()) || (this.getArea() != null
						&& castOther.getArea() != null && this.getArea()
						.equals(castOther.getArea())))
				&& ((this.getDomain() == castOther.getDomain()) || (this
						.getDomain() != null && castOther.getDomain() != null && this
						.getDomain().equals(castOther.getDomain())))
				&& ((this.getDatastart() == castOther.getDatastart()) || (this
						.getDatastart() != null
						&& castOther.getDatastart() != null && this
						.getDatastart().equals(castOther.getDatastart())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getUid() == null ? 0 : this.getUid().hashCode());
		result = 37 * result
				+ (getArea() == null ? 0 : this.getArea().hashCode());
		result = 37 * result
				+ (getDomain() == null ? 0 : this.getDomain().hashCode());
		result = 37 * result
				+ (getDatastart() == null ? 0 : this.getDatastart().hashCode());
		return result;
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

}