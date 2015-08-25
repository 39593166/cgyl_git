package com.utoo.chunguanyouli.dbentity;

/**
 * GFactoryId entity. @author MyEclipse Persistence Tools
 */

public class GFactoryId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String introduce;
	private String citycode;
	private String logo;
	private String area;
	private String idea;
	private String promise;
	private String brandname;
	private String barndlogo;
	private String video;
	private String recommend1;
	private String recommend2;
	private String recommend3;
	private String recommend4;
	private Integer numstar;
	private String pic1;
	private String pic2;
	private String pic3;
	private String pic4;
	private Integer userid;
	private String telpro;
	private String tel;
	private String site;
	private String piczhizhao;
	private String picdaima;

	// Constructors

	/** default constructor */
	public GFactoryId() {
	}

	/** minimal constructor */
	public GFactoryId(Integer id, String name, String introduce,
			String citycode, String logo, String area, String idea,
			String promise, Integer userid) {
		this.id = id;
		this.name = name;
		this.introduce = introduce;
		this.citycode = citycode;
		this.logo = logo;
		this.area = area;
		this.idea = idea;
		this.promise = promise;
		this.userid = userid;
	}

	/** full constructor */
	public GFactoryId(Integer id, String name, String introduce,
			String citycode, String logo, String area, String idea,
			String promise, String brandname, String barndlogo, String video,
			String recommend1, String recommend2, String recommend3,
			String recommend4, Integer numstar, String pic1, String pic2,
			String pic3, String pic4, Integer userid, String telpro,
			String tel, String site, String piczhizhao, String picdaima) {
		this.id = id;
		this.name = name;
		this.introduce = introduce;
		this.citycode = citycode;
		this.logo = logo;
		this.area = area;
		this.idea = idea;
		this.promise = promise;
		this.brandname = brandname;
		this.barndlogo = barndlogo;
		this.video = video;
		this.recommend1 = recommend1;
		this.recommend2 = recommend2;
		this.recommend3 = recommend3;
		this.recommend4 = recommend4;
		this.numstar = numstar;
		this.pic1 = pic1;
		this.pic2 = pic2;
		this.pic3 = pic3;
		this.pic4 = pic4;
		this.userid = userid;
		this.telpro = telpro;
		this.tel = tel;
		this.site = site;
		this.piczhizhao = piczhizhao;
		this.picdaima = picdaima;
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

	public String getIntroduce() {
		return this.introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getCitycode() {
		return this.citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getIdea() {
		return this.idea;
	}

	public void setIdea(String idea) {
		this.idea = idea;
	}

	public String getPromise() {
		return this.promise;
	}

	public void setPromise(String promise) {
		this.promise = promise;
	}

	public String getBrandname() {
		return this.brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public String getBarndlogo() {
		return this.barndlogo;
	}

	public void setBarndlogo(String barndlogo) {
		this.barndlogo = barndlogo;
	}

	public String getVideo() {
		return this.video;
	}

	public void setVideo(String video) {
		this.video = video;
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

	public Integer getNumstar() {
		return this.numstar;
	}

	public void setNumstar(Integer numstar) {
		this.numstar = numstar;
	}

	public String getPic1() {
		return this.pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getPic2() {
		return this.pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public String getPic3() {
		return this.pic3;
	}

	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}

	public String getPic4() {
		return this.pic4;
	}

	public void setPic4(String pic4) {
		this.pic4 = pic4;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getTelpro() {
		return this.telpro;
	}

	public void setTelpro(String telpro) {
		this.telpro = telpro;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSite() {
		return this.site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getPiczhizhao() {
		return this.piczhizhao;
	}

	public void setPiczhizhao(String piczhizhao) {
		this.piczhizhao = piczhizhao;
	}

	public String getPicdaima() {
		return this.picdaima;
	}

	public void setPicdaima(String picdaima) {
		this.picdaima = picdaima;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GFactoryId))
			return false;
		GFactoryId castOther = (GFactoryId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getIntroduce() == castOther.getIntroduce()) || (this
						.getIntroduce() != null
						&& castOther.getIntroduce() != null && this
						.getIntroduce().equals(castOther.getIntroduce())))
				&& ((this.getCitycode() == castOther.getCitycode()) || (this
						.getCitycode() != null
						&& castOther.getCitycode() != null && this
						.getCitycode().equals(castOther.getCitycode())))
				&& ((this.getLogo() == castOther.getLogo()) || (this.getLogo() != null
						&& castOther.getLogo() != null && this.getLogo()
						.equals(castOther.getLogo())))
				&& ((this.getArea() == castOther.getArea()) || (this.getArea() != null
						&& castOther.getArea() != null && this.getArea()
						.equals(castOther.getArea())))
				&& ((this.getIdea() == castOther.getIdea()) || (this.getIdea() != null
						&& castOther.getIdea() != null && this.getIdea()
						.equals(castOther.getIdea())))
				&& ((this.getPromise() == castOther.getPromise()) || (this
						.getPromise() != null && castOther.getPromise() != null && this
						.getPromise().equals(castOther.getPromise())))
				&& ((this.getBrandname() == castOther.getBrandname()) || (this
						.getBrandname() != null
						&& castOther.getBrandname() != null && this
						.getBrandname().equals(castOther.getBrandname())))
				&& ((this.getBarndlogo() == castOther.getBarndlogo()) || (this
						.getBarndlogo() != null
						&& castOther.getBarndlogo() != null && this
						.getBarndlogo().equals(castOther.getBarndlogo())))
				&& ((this.getVideo() == castOther.getVideo()) || (this
						.getVideo() != null && castOther.getVideo() != null && this
						.getVideo().equals(castOther.getVideo())))
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
				&& ((this.getNumstar() == castOther.getNumstar()) || (this
						.getNumstar() != null && castOther.getNumstar() != null && this
						.getNumstar().equals(castOther.getNumstar())))
				&& ((this.getPic1() == castOther.getPic1()) || (this.getPic1() != null
						&& castOther.getPic1() != null && this.getPic1()
						.equals(castOther.getPic1())))
				&& ((this.getPic2() == castOther.getPic2()) || (this.getPic2() != null
						&& castOther.getPic2() != null && this.getPic2()
						.equals(castOther.getPic2())))
				&& ((this.getPic3() == castOther.getPic3()) || (this.getPic3() != null
						&& castOther.getPic3() != null && this.getPic3()
						.equals(castOther.getPic3())))
				&& ((this.getPic4() == castOther.getPic4()) || (this.getPic4() != null
						&& castOther.getPic4() != null && this.getPic4()
						.equals(castOther.getPic4())))
				&& ((this.getUserid() == castOther.getUserid()) || (this
						.getUserid() != null && castOther.getUserid() != null && this
						.getUserid().equals(castOther.getUserid())))
				&& ((this.getTelpro() == castOther.getTelpro()) || (this
						.getTelpro() != null && castOther.getTelpro() != null && this
						.getTelpro().equals(castOther.getTelpro())))
				&& ((this.getTel() == castOther.getTel()) || (this.getTel() != null
						&& castOther.getTel() != null && this.getTel().equals(
						castOther.getTel())))
				&& ((this.getSite() == castOther.getSite()) || (this.getSite() != null
						&& castOther.getSite() != null && this.getSite()
						.equals(castOther.getSite())))
				&& ((this.getPiczhizhao() == castOther.getPiczhizhao()) || (this
						.getPiczhizhao() != null
						&& castOther.getPiczhizhao() != null && this
						.getPiczhizhao().equals(castOther.getPiczhizhao())))
				&& ((this.getPicdaima() == castOther.getPicdaima()) || (this
						.getPicdaima() != null
						&& castOther.getPicdaima() != null && this
						.getPicdaima().equals(castOther.getPicdaima())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getIntroduce() == null ? 0 : this.getIntroduce().hashCode());
		result = 37 * result
				+ (getCitycode() == null ? 0 : this.getCitycode().hashCode());
		result = 37 * result
				+ (getLogo() == null ? 0 : this.getLogo().hashCode());
		result = 37 * result
				+ (getArea() == null ? 0 : this.getArea().hashCode());
		result = 37 * result
				+ (getIdea() == null ? 0 : this.getIdea().hashCode());
		result = 37 * result
				+ (getPromise() == null ? 0 : this.getPromise().hashCode());
		result = 37 * result
				+ (getBrandname() == null ? 0 : this.getBrandname().hashCode());
		result = 37 * result
				+ (getBarndlogo() == null ? 0 : this.getBarndlogo().hashCode());
		result = 37 * result
				+ (getVideo() == null ? 0 : this.getVideo().hashCode());
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
				+ (getNumstar() == null ? 0 : this.getNumstar().hashCode());
		result = 37 * result
				+ (getPic1() == null ? 0 : this.getPic1().hashCode());
		result = 37 * result
				+ (getPic2() == null ? 0 : this.getPic2().hashCode());
		result = 37 * result
				+ (getPic3() == null ? 0 : this.getPic3().hashCode());
		result = 37 * result
				+ (getPic4() == null ? 0 : this.getPic4().hashCode());
		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result
				+ (getTelpro() == null ? 0 : this.getTelpro().hashCode());
		result = 37 * result
				+ (getTel() == null ? 0 : this.getTel().hashCode());
		result = 37 * result
				+ (getSite() == null ? 0 : this.getSite().hashCode());
		result = 37
				* result
				+ (getPiczhizhao() == null ? 0 : this.getPiczhizhao()
						.hashCode());
		result = 37 * result
				+ (getPicdaima() == null ? 0 : this.getPicdaima().hashCode());
		return result;
	}

}