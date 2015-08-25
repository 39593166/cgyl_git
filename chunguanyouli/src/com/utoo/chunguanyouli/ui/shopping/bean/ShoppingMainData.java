package com.utoo.chunguanyouli.ui.shopping.bean;

import java.io.Serializable;
import java.util.List;

import com.utoo.chunguanyouli.dbentity.CgGoodsId;
import com.utoo.chunguanyouli.entity.Banner;

public class ShoppingMainData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2207806547903665341L;
	private List<Banner> banners;
	private List<CgGoodsId> worthBuygoodsList;
	private List<CgGoodsId> faverateGoodsList;
	private HeadRecommend hr;
	public List<Banner> getBanners() {
		return banners;
	}

	public void setBanners(List<Banner> banners) {
		this.banners = banners;
	}

	public List<CgGoodsId> getWorthBuygoodsList() {
		return worthBuygoodsList;
	}

	public void setWorthBuygoodsList(List<CgGoodsId> worthBuygoodsList) {
		this.worthBuygoodsList = worthBuygoodsList;
	}

	public List<CgGoodsId> getFaverateGoodsList() {
		return faverateGoodsList;
	}

	public void setFaverateGoodsList(List<CgGoodsId> faverateGoodsList) {
		this.faverateGoodsList = faverateGoodsList;
	}

	public HeadRecommend getHr() {
		return hr;
	}

	public void setHr(HeadRecommend hr) {
		this.hr = hr;
	}

}