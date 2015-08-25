package com.utoo.chunguanyouli.entity;

import java.io.Serializable;

public class Banner implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6987799242923830988L;
	private String bannerId;
	private String bannerImg;
	private int bannerType;
	private String value;
	private String goodsId;
	private String webUrl;

	public String getBannerId() {
		return bannerId;
	}

	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}

	public String getBannerImg() {
		return bannerImg;
	}

	public void setBannerImg(String bannerImage) {
		this.bannerImg = bannerImage;
	}

	public int getBannerType() {
		return bannerType;
	}

	public void setBannerType(int bannerType) {
		this.bannerType = bannerType;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
