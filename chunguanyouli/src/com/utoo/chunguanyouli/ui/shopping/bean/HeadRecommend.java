package com.utoo.chunguanyouli.ui.shopping.bean;

import java.io.Serializable;

public class HeadRecommend implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9090256148239774664L;
	private String title;
	private int goodsId;
	private boolean isHot;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public boolean isHot() {
		return isHot;
	}

	public void setHot(boolean isHot) {
		this.isHot = isHot;
	}

}
